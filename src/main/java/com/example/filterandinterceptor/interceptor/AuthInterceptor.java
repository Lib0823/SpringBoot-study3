package com.example.filterandinterceptor.interceptor;

import com.example.filterandinterceptor.annotation.AuthUser;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    // 주로 인증에 사용 (권한이 있는지)

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        boolean validAuthUser = checkValidAccessAnnotation(handler, AuthUser.class);
        System.out.println("annotation check : "+validAuthUser);
        if(validAuthUser){
            return true;
        }

        return false;
    }

    private boolean checkValidAccessAnnotation(Object handler, Class clazz) {

        // Resource에 대한 요청이면 무조건 넘김
        if(handler instanceof ResourceHttpRequestHandler){
            System.out.println("리소스 요청 class "+clazz.getName());
            return true;
        }

        // Auth 어노테이션이 붙어있으면 검사
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        if(null != handlerMethod.getMethodAnnotation(clazz) || null != handlerMethod.getBeanType().getAnnotation(clazz)){
            System.out.println("어노테이션 체크 class "+clazz.getName());
            return true;
        }

        return false;
    }
}