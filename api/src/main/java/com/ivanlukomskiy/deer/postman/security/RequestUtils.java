package com.ivanlukomskiy.deer.postman.security;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ivanl <ilukomskiy@sbdagroup.com> on 14.12.2017.
 */
@Service
class RequestUtils {
    static final String TOKEN_HEADER_NAME = "DEER-POSTMAN-TOKEN";

    static String getToken(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getHeader(TOKEN_HEADER_NAME);
    }
}
