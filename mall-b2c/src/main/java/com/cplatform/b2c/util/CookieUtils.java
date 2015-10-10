package com.cplatform.b2c.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * CookieUtils
 *
 * @author David Czarnecki
 * @since blojsom 2.03
 * @version $Id: CookieUtils.java,v 1.2.2.1 2005/07/21 14:11:04 johnan Exp $
 */
public class CookieUtils {

    /**
     * Return a cookie given a particular key
     *
     * @param httpServletRequest Request
     * @param cookieKey Cookie key
     * @return <code>Cookie</code> of the requested key or <code>null</code> if no cookie
     * under that name is found
     */
    public static Cookie getCookie(HttpServletRequest httpServletRequest, String cookieKey) {
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies == null) {
            return null;
        }

        for (int i = 0; i < cookies.length; i++) {
            Cookie cookie = cookies[i];
            if (cookie.getName().equals(cookieKey)) {
                return cookie;
            }
        }

        return null;
    }

    /**
     * Add a cookie with a key and value to the response
     *
     * @param httpServletResponse Response
     * @param cookieKey Cookie key
     * @param cookieValue Cookie value
     */
    public static void addCookie(HttpServletResponse httpServletResponse,
                                  int cookieExpiration,
                                  String cookieKey,
                                  String cookieValue) {
        Cookie cookie = new Cookie(cookieKey, cookieValue);
        cookie.setMaxAge(cookieExpiration);
        httpServletResponse.addCookie(cookie);
    }

    /**
     * Add a cookie with a key and value to the response
     *
     * @param httpServletResponse Response
     * @param cookieKey Cookie key
     * @param cookieValue Cookie value
     */
    public static void addCookieToRoot(HttpServletResponse httpServletResponse,
                                 int cookieExpiration,
                                 String cookieKey,
                                 String cookieValue) {
        Cookie cookie = new Cookie(cookieKey, cookieValue);
        cookie.setPath("/");
        cookie.setMaxAge(cookieExpiration);
        httpServletResponse.addCookie(cookie);
    }

    /**
     * Remove a cookie with a key and value to the response
     *
     * @param httpServletResponse Response
     * @param cookieKey Cookie key
     */
    public static void removeCookieFromRoot(HttpServletResponse httpServletResponse, String cookieKey) {
        Cookie cookie = new Cookie(cookieKey, null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        httpServletResponse.addCookie(cookie);
    }
}
