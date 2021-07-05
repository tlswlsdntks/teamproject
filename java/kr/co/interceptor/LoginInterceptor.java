package kr.co.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.co.domain.MemberDTO;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = request.getSession();
		MemberDTO dto = (MemberDTO) session.getAttribute("login");
		if(dto!=null)
			session.invalidate();

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		Map<String, Object> map = modelAndView.getModel();
		Object dto = map.get("login");

		HttpSession session = request.getSession();

		if (dto != null) {
			session.setAttribute("login", dto);
			response.sendRedirect("/board/list");
		} else
			response.sendRedirect("/member/loginGet");

	}

}
