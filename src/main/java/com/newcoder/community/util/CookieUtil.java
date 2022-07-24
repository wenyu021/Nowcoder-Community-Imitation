package com.newcoder.community.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Wenyu Wang
 * @project community
 * @date 2022-07
 * @Description
 */
public class CookieUtil {
    public static String getValue(HttpServletRequest request, String name) {
        if (request == null || name == null) {
            throw new IllegalArgumentException("empty param");
        }

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for(Cookie cookie : cookies){
                if(cookie.getName().equals(name)){
                    return cookie.getValue();
                }

            }
        }
        return null;
    }
}
