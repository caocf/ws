package com.cplatform.mall.dc.interceptors;

import net.sf.json.JSONObject;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import com.cplatform.mall.dc.entity.DcUser;
import com.cplatform.mall.dc.model.SessionUser;
import com.cplatform.mall.dc.utils.JsonRespWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * 我的应用 模块的验证。
 * 
 * @author  chengyao / 北京宽连十方
 * @version 1.0, Jan 6, 2009
 */
public class AuthenticationInterceptor extends AbstractExcludePathInterceptor {

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public boolean handleInternal(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        SessionUser sessionUser = SessionUser.getSessionUser();

        String contextPath = request.getSession().getServletContext().getContextPath();


        // System.out.println(request.getHeader("Accept"));
        // System.out.println(request.getHeader("X-Requested-With"));
        // not login
        if (sessionUser == null) {

            String redirectUrl = contextPath + "/login?p=" + buildServiceUrl(request, response, true);
            String accept = request.getHeader("Accept");
            String xrequest = request.getHeader("X-Requested-With");

            // ajax request
            if (xrequest != null) {

                // ajax request must get the referer url to redirect
                String referer = request.getHeader("Referer");
                if (referer != null) {
                    redirectUrl = contextPath + "/login?p=" + buildServiceUrl(referer, response, true);
                } else {
                    redirectUrl = contextPath + "/login";
                }

                String result;
                // json proccess result
                if (accept != null && accept.contains("application/json")) {
                    JsonRespWrapper resp = JsonRespWrapper.json().json("needLogin", true)
                            .json("url", redirectUrl);
                    result = JSONObject.fromObject(resp).toString();
                    response.setContentType("application/json;charset=UTF-8");
                // normal direct hint page
                } else {
                    result = "<script>\nwindow.location.href='" + redirectUrl
                            + "';\n</script><a href='" + redirectUrl
                            + "'>登录已过期，将跳到登录页面，如未跳转，请点击</a>";
                    response.setCharacterEncoding("utf-8");
                    response.setContentType("text/html;charset=UTF-8");
                }

                response.setCharacterEncoding("utf-8");
                PrintWriter out = response.getWriter();
                out.print(result);
                out.flush();
                out.close();
                return false;
            }

            response.sendRedirect(redirectUrl);
            return false;
        }

        // pause
        if (sessionUser.getStatus() == DcUser.STATUS_PAUSE) {
            response.sendRedirect(contextPath + "/info/locked");
            return false;
        }

        return true;
    }


    /**
     * 处理回传链接
     * @param request
     * @param response
     * @param encode
     * @return
     */
    private String buildServiceUrl(HttpServletRequest request, HttpServletResponse response, boolean encode) {
        StringBuilder buffer = new StringBuilder();
        buffer.append(request.getRequestURI());
        if (StringUtils.hasText(request.getQueryString())) {
            buffer.append("?");
            buffer.append(request.getQueryString());
        }
        final String returnValue = encode ? response.encodeURL(buffer.toString()) : buffer.toString();
        return returnValue;
    }

    /**
     * 处理回传链接
     * @param url
     * @param response
     * @param encode
     * @return
     */
    private String buildServiceUrl(String url, HttpServletResponse response, boolean encode) throws MalformedURLException {
        StringBuilder buffer = new StringBuilder();
        URL u = new URL(url);
        buffer.append(u.getPath());
        if (StringUtils.hasText(u.getQuery())) {
            buffer.append("?");
            buffer.append(u.getQuery());
        }
        final String returnValue = encode ? response.encodeURL(buffer.toString()) : buffer.toString();
        return returnValue;
    }


}
