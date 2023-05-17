package com.yst.filter;

import com.yst.controller.Code;
import com.yst.controller.Result;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.alibaba.fastjson.JSON;

@WebFilter(filterName = "LoginCheckFilter", urlPatterns = "/*")
public class LoginCheckFilter implements Filter {

    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Result result = new Result(Code.UNLOGIN,null,"UNLOGIN");

        System.out.println("拦截到请求：{"+request.getRequestURI()+"}");

        String requestURI = request.getRequestURI();
        String[] uris = new String[]{
                "/",
                "/login",
                "/pages/**",
                "/css/**",
                "/js/**",
                "/plugins/**"
        };

        boolean check = check(uris,requestURI);
        if(check){
            System.out.println("本次请求{"+requestURI+"}不需要处理，放行");
            filterChain.doFilter(request,response);
            return;
        }

        if(request.getSession().getAttribute("userName")!=null){
            System.out.println("用户已登录，用户id为：{"+request.getSession().getAttribute("userName")+"}");
            filterChain.doFilter(request,response);
            return;
        }

        System.out.println("用户未登录，拦截请求：{"+requestURI+"}");
        response.getWriter().write(JSON.toJSONString(result));
        return;
    }

    public boolean check(String[] uris,String requestURI){
        for (String uri : uris) {
            if(PATH_MATCHER.match(uri,requestURI)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}
}
