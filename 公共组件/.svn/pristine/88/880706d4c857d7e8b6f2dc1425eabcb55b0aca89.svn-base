package com.cplatform.cas.client;

import net.sf.json.JSONObject;
import org.jasig.cas.client.authentication.DefaultGatewayResolverImpl;
import org.jasig.cas.client.authentication.GatewayResolver;
import org.jasig.cas.client.util.AbstractCasFilter;
import org.jasig.cas.client.util.CommonUtils;
import org.jasig.cas.client.validation.Assertion;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

/**
 * 扩展默认的client filter， 使得可以配置忽略校验地址的验证过滤器.
 * <p/>
 * Copyright: Copyright (c) 13-5-29 下午1:55
 * <p/>
 * Company: 北京宽连十方数字技术有限公司
 * <p/>
 * Author: chengyao
 * <p/>
 * Version: 1.0
 * <p/>
 */
public class ExcludeableAuthenticationFilter  extends AbstractCasFilter {

    /**
     * The URL to the CAS Server login.
     */
    private String casServerLoginUrl;

    /**
     * Whether to send the renew request or not.
     */
    private boolean renew = false;

    /**
     * Whether to send the gateway request or not.
     */
    private boolean gateway = false;

    private GatewayResolver gatewayStorage = new DefaultGatewayResolverImpl();

    /**
     * 不做权限验证的地址列表。可以使前缀路径或是具体的url
     */
    private List<String> ignorePaths;



    protected void initInternal(final FilterConfig filterConfig) throws ServletException {
        if (!isIgnoreInitConfiguration()) {
            super.initInternal(filterConfig);
            setCasServerLoginUrl(getPropertyFromInitParams(filterConfig, "casServerLoginUrl", null));
            log.trace("Loaded CasServerLoginUrl parameter: " + this.casServerLoginUrl);
            setRenew(parseBoolean(getPropertyFromInitParams(filterConfig, "renew", "false")));
            log.trace("Loaded renew parameter: " + this.renew);
            setGateway(parseBoolean(getPropertyFromInitParams(filterConfig, "gateway", "false")));
            log.trace("Loaded gateway parameter: " + this.gateway);
            setIgnorePaths(parseList(getPropertyFromInitParams(filterConfig, "ignorePaths", null)));
            log.trace("Loaded ignorePaths parameter: " + this.ignorePaths.size());

            final String gatewayStorageClass = getPropertyFromInitParams(filterConfig, "gatewayStorageClass", null);

            if (gatewayStorageClass != null) {
                try {
                    this.gatewayStorage = (GatewayResolver) Class.forName(gatewayStorageClass).newInstance();
                } catch (final Exception e) {
                    log.error(e,e);
                    throw new ServletException(e);
                }
            }
        }
    }

    public void init() {
        super.init();
        CommonUtils.assertNotNull(this.casServerLoginUrl, "casServerLoginUrl cannot be null.");
    }

    public final void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        final HttpSession session = request.getSession(false);

        final Assertion assertion = session != null ? (Assertion) session.getAttribute(CONST_CAS_ASSERTION) : null;

        if (assertion != null) {
            filterChain.doFilter(request, response);
            return;
        }

        // 属于忽略地址
        if (match(request)) {
            filterChain.doFilter(servletRequest,  servletResponse);
            return;
        }

        final String serviceUrl = constructServiceUrl(request, response);
        final String ticket = CommonUtils.safeGetParameter(request,getArtifactParameterName());
        final boolean wasGatewayed = this.gatewayStorage.hasGatewayedAlready(request, serviceUrl);

        if (CommonUtils.isNotBlank(ticket) || wasGatewayed) {
            filterChain.doFilter(request, response);
            return;
        }

        final String modifiedServiceUrl;

        log.debug("no ticket and no assertion found");
        if (this.gateway) {
            log.debug("setting gateway attribute in session");
            modifiedServiceUrl = this.gatewayStorage.storeGatewayInformation(request, serviceUrl);
        } else {
            modifiedServiceUrl = serviceUrl;
        }

        if (log.isDebugEnabled()) {
            log.debug("Constructed service url: " + modifiedServiceUrl);
        }
        final String urlToRedirectTo = CommonUtils.constructRedirectUrl(this.casServerLoginUrl, getServiceParameterName(), modifiedServiceUrl, this.renew, this.gateway);

        if (log.isDebugEnabled()) {
            log.debug("redirecting to \"" + urlToRedirectTo + "\"");
        }


        String accept = request.getHeader("Accept");
        String xrequest = request.getHeader("X-Requested-With");
        // ajax request
        if (xrequest != null) {
            String result;
            // json proccess result
            if (accept != null && accept.contains("application/json")) {
                JSONObject obj = new JSONObject();
                obj.put("needLogin", true); obj.put("url", urlToRedirectTo);
                result = obj.toString();
                response.setContentType("application/json;charset=UTF-8");
                // normal direct hint page
            } else {
                result = "<script>\nwindow.location.href='" + urlToRedirectTo
                        + "';\n</script><a href='" + urlToRedirectTo
                        + "'>登录已过期，将跳到登录页面，如未跳转，请点击</a>";
                response.setCharacterEncoding("utf-8");
                response.setContentType("text/html;charset=UTF-8");
            }

            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            out.print(result);
            out.flush();
            out.close();
            return;
        }

        response.sendRedirect(urlToRedirectTo);
    }

    public final void setRenew(final boolean renew) {
        this.renew = renew;
    }

    public final void setGateway(final boolean gateway) {
        this.gateway = gateway;
    }

    public final void setCasServerLoginUrl(final String casServerLoginUrl) {
        this.casServerLoginUrl = casServerLoginUrl;
    }

    public final void setGatewayStorage(final GatewayResolver gatewayStorage) {
        this.gatewayStorage = gatewayStorage;
    }

    public List<String> getIgnorePaths() {
        return ignorePaths;
    }

    public void setIgnorePaths(List<String> ignorePaths) {
        this.ignorePaths = ignorePaths;
    }

    protected final List<String> parseList(final String value) {
        if (value == null) {
            return null;
        }
        String[] result = value.split(",");
        return Arrays.asList(result);
    }


    /**
     * request path 是否匹配忽略地址
     * @param request
     * @return
     */
    private boolean match(HttpServletRequest request) {
        if (ignorePaths == null) {
            return false;
        }

        String url = request.getServletPath();
        PathMatcher matcher = new AntPathMatcher();
        for (String ignorePath : ignorePaths) {
            if (matcher.match(ignorePath, url)) {
                return true;
            }
        }
        return false;
    }
}