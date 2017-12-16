package com.ivanlukomskiy.deer.postman.security;

import com.ivanlukomskiy.deer.postman.DeerSantaException;
import com.ivanlukomskiy.deer.postman.annotations.Access;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import static com.ivanlukomskiy.deer.postman.security.RequestUtils.TOKEN_HEADER_NAME;

/**
 * Created by ivanl <ilukomskiy@sbdagroup.com> on 14.12.2017.
 */
@Component
@Aspect
public class AccessProcessor {

    private static final Logger logger = LoggerFactory.getLogger(AccessProcessor.class);

    @Autowired
    private TokenService tokenService;

    @Before("execution (* *(..)) && @annotation(com.ivanlukomskiy.deer.postman.annotations.Access)")
    @Transactional(readOnly = true)
    public void checkToken(JoinPoint joinPoint) throws Exception {

        if (RequestContextHolder.getRequestAttributes() == null) {
            throw new DeerSantaException("bad-token");
        }

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        String token = RequestUtils.getToken(request);
        if (token == null) {
            throw new DeerSantaException("no-token-header");
        }
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getMethod().getName();
        Class<?>[] parameterTypes = signature.getMethod().getParameterTypes();
        Access annotation =
                joinPoint.getTarget().getClass().getDeclaredMethod(methodName, parameterTypes)
                        .getAnnotation(Access.class);
        boolean isTokenValid = tokenService.isTokenMatchesRoles(token, annotation.value());
        if (!isTokenValid) {
            throw new DeerSantaException("access-denied");
        }
    }
}
