package com.xidong.orderFoodOnline.util;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LoginFiter
 */
public class CheckLoginFiter implements Filter {

    /**
     * Default constructor. 
     */
    public CheckLoginFiter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		 HttpServletRequest req = (HttpServletRequest)request;  
	        HttpServletResponse resp = (HttpServletResponse)response;  
	        HttpSession session = req.getSession();  
	        //会话对象取得用户信息
	       String userId = (String) session.getAttribute("userId");  
	        //获取请求地址  
	        String url[] = req.getRequestURI().split("/");  
	        //登录页面、登录请求和系统首页操作不用过滤  
	        if(url[url.length-1].startsWith("login.do")||url[url.length-1].startsWith("loginPage.do")){  
	            chain.doFilter(req, resp);  
	            return;  
	        }
	        //判断是否已经登录，若是没有登录返回登录页面  
	        if(userId==null||"".equals(userId)){  
	            resp.sendRedirect("/index.jsp");
	        }else{  
	            chain.doFilter(req, resp);  
	        }  
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
