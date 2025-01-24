//package com.digishopkart.order_service.config;
//
//import feign.RequestInterceptor;
//import feign.RequestTemplate;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.oauth2.jwt.Jwt;
//
//@Configuration
//public class FeignClientConfig {
//
//    @Bean
//    public RequestInterceptor requestInterceptor(){
//        return new RequestInterceptor() {
//            @Override
//            public void apply(RequestTemplate template) {
//                Object principal = SecurityContextHolder.getContext().getAuthentication();
//
//                if (principal instanceof Jwt){
//                    String jwtToken = ((Jwt) principal).getTokenValue();
//                    template.header("Authorization","Bearer "+jwtToken);
//                }
//            }
//        };
//    }
//}
