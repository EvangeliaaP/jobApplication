package com.example.demo.config;

import ch.qos.logback.classic.Logger;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.UUID;

@Configuration
@Log4j2 // Logger for outer class (optional here)
public class LoggingConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggingInterceptor());
    }

    public static class LoggingInterceptor implements HandlerInterceptor {




        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
            String requestId = UUID.randomUUID().toString();
            MDC.put("requestId", requestId);
            MDC.put("method", request.getMethod());
            MDC.put("url", request.getRequestURL().toString());
            MDC.put("user", "Anonymous"); // You can later replace this with actual user info
            MDC.put("clientIp", request.getRemoteAddr());
            request.setAttribute("startTime", System.currentTimeMillis());
            return true;
        }

        @Override
        public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
            // No action needed here for now
        }

        @Override
        public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
            long startTime = (Long) request.getAttribute("startTime");
            long duration = System.currentTimeMillis() - startTime;
            String responseCode = String.valueOf(response.getStatus());

            MDC.put("responseCode", responseCode);
            MDC.put("responseTime", String.valueOf(duration));

            //log.info("Response Code: {}, Duration: {} ms", responseCode, duration);

            MDC.clear();
        }
    }
}

