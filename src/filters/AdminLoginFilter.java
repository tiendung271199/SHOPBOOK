package filters;

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

import models.User;

public class AdminLoginFilter implements Filter {

	public AdminLoginFilter() {

	}

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		User adminLogin = (User) session.getAttribute("adminLogin");
		if (adminLogin != null) {
			if (adminLogin.getRole() != 0) {
				chain.doFilter(request, response);
				return;
			} else {
				res.sendRedirect(req.getContextPath() + "/auth/logout");
				return;
			}
		} else {
			res.sendRedirect(req.getContextPath() + "/auth/login");
			return;
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
