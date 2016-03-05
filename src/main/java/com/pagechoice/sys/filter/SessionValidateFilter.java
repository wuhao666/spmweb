package com.pagechoice.sys.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pagechoice.sys.entity.User;
import com.pagechoice.sys.utils.UserUtil;

public class SessionValidateFilter implements Filter {
	public void destroy() {
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession();
		User user = UserUtil.getUserFromSession(session);
		if (request.getRequestURI().indexOf("rest") > 0) {
			chain.doFilter(request, response);
			return;
		}
		if (request.getRequestURI().indexOf("login.do") > 0) {
			chain.doFilter(request, response);
			return;
		}
		if (session == null || user == null) {
			String PATH = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
			response.sendRedirect(PATH + "/application/page/login.jsp");
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {

	}

}
