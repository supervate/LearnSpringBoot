package com.learn.filters;

import cn.rq.es.utils.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EncodingFilter extends HttpFilter {

    public String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(StringUtils.isBlank(encoding)?"utf-8":encoding);
        response.setCharacterEncoding(StringUtils.isBlank(encoding)?"utf-8":encoding);
        this.doFilter((HttpServletRequest)request, (HttpServletResponse)response, chain);
    }

    public void destroy() {

    }
}
