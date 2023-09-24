package com.rebe.returnstudy.Configuration;

import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class Config {

    private final CustomFilter customFilter;

    @Bean
    public FilterRegistrationBean<Filter> firstFilterRegister() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(customFilter); //필터 등록
        return registrationBean;
    }

}
