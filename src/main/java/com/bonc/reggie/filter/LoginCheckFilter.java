package com.bonc.reggie.filter;

import com.alibaba.fastjson.JSON;
import com.bonc.reggie.common.BaseContext;
import com.bonc.reggie.common.R;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 检查用户是否已经登录
 */
@WebFilter(filterName = "loginFilter",urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {

    // 路径匹配器，支持通配符
    public  static final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        /**
         * 1、获取本次请求的URI
         * 2、判断本次请求是否需要处理
         * 3、如果不需要处理，则直接放行
         * 4、判断登录状态，如果已登录，则直接放行
         * 5、如果未登录则返回未登录结果
         */
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        log.info("拦截到请求: {}",request.getRequestURI());

        // 1、获取本次请求的URI
        String requestURI = request.getRequestURI();

        // 2、判断本次请求是否需要处理
        String [] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
                "/common/**",
                "/user/sendMsg",
                "/user/login"
        };
        boolean check = checkUrl(urls,requestURI);

        // 3、如果不需要处理，则直接放行
        if(check){
            log.info("本次请求{}不需要处理",request.getRequestURI());
            filterChain.doFilter(request,response);
            return;
        }

        // 4-1、判断登录状态，如果已登录，则直接放行
        if(request.getSession().getAttribute("employee")!=null){
            log.info("用户已登录，用户id: {}",request.getSession().getAttribute("employee"));
            Long empId = (Long) request.getSession().getAttribute("employee");
            BaseContext.setCurrentId(empId);
            filterChain.doFilter(request,response);
            return;
        }

        // 4-2、判断登录状态，如果已登录，则直接放行
        if(request.getSession().getAttribute("user")!=null){
            log.info("用户已登录，用户id: {}",request.getSession().getAttribute("user"));
            Long userId = (Long) request.getSession().getAttribute("user");
            BaseContext.setCurrentId(userId);
            filterChain.doFilter(request,response);
            return;
        }

        // 5、如果未登录则返回未登录结果，通过输出流方式向客户端页面响应数据
        log.info("用户未登录");
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));

    }

    /**
     * 路径匹配，检查本次请求是否需要放行
     * @param urls
     * @param requestUrl
     * @return
     */
    public boolean checkUrl(String[] urls,String requestUrl){
        for (String url : urls) {
            if(antPathMatcher.match(url,requestUrl)){
                return true;
            }
        }
        return false;
    }

}
