package exceptionhandling.classroom.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Component
public class InterceptorsConfig implements WebMvcConfigurer {

    @Autowired
    MyInterceptor1 myInterceptor1;

    @Autowired
    MyInterceptor2 myInterceptor2;

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor1);
        registry.addInterceptor(myInterceptor2).addPathPatterns("/greet/result/examine");
    }
}

