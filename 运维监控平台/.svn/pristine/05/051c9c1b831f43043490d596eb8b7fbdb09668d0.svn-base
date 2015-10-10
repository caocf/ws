package com.cplatform.mall.dc.interceptors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.cplatform.mall.dc.entity.DcUser;
import com.cplatform.mall.dc.model.SessionUser;
import com.cplatform.mall.dc.service.DcUserService;
import com.cplatform.mall.dc.utils.AppConfig;


/**
 * cookies 模拟登录 过滤器
 * 
 * @author  chengyao / 北京宽连十方
 * @version 1.0, Jan 6, 2009
 */
public class CookiesLoginInterceptor extends AbstractExcludePathInterceptor {

    @Autowired
    DcUserService dcUserService;

    @Autowired
    AppConfig appConfig;

    public boolean handleInternal(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        SessionUser sessionUser = SessionUser.getSessionUser();

        // 已登录情况
        if (sessionUser != null && sessionUser.getId() != null /* patch for id lost bug, not find reason, yet*/) {
            return true;
        }

        // 尝试读取cookies
        Cookie[] cookies = request.getCookies();

        // 检查空cookie
        if (cookies == null) {
            return true;
        }

        for (Cookie cookie : cookies) {
            // 找到登录cookie
            if (AppConfig.COOKIE_TOKEN.equals(cookie.getName())) {

                String token = cookie.getValue();
                // 尝试解析token，正确的话，模拟登录
                try {

                    DcUser user = dcUserService.parseToken(token);
                    dcUserService.writeLoginSession(request, user);

                } catch (Exception ex) {
                    // clear cookie token
                	dcUserService.clearCookieToken(response);
                    // ignore;
                }

                break;
            }
        }

        return true;
    }


}
