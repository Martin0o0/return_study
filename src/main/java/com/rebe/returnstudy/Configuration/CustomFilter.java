package com.rebe.returnstudy.Configuration;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class CustomFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        log.info("Return Study Custom 필터 생성");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("현재, Custom Filter 통과중");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
        log.info("Return Study Custom 필터 소멸");
    }
}
