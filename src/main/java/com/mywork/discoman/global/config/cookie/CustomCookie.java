package com.mywork.discoman.global.config.cookie;

import javax.servlet.http.Cookie;

public class CustomCookie extends Cookie {
    public CustomCookie(String name, String value) {
        super(name, value);
        super.setHttpOnly(true);
        super.setPath("/");
    }

    public CustomCookie(String name, String value, int expired){
        super(name, value);
        super.setHttpOnly(true);
        super.setPath("/");
        super.setMaxAge(expired);
    }
}
