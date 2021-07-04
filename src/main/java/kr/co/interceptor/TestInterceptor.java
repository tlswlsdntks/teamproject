package kr.co.interceptor;

import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class TestInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("Prehandler:::::::::::::::::::");
		
		HandlerMethod method = (HandlerMethod)handler;
		Method methodObj = method.getMethod();
		
		System.out.println(method.getBean());
		System.out.println(methodObj);
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		System.out.println("Posthandler:::::::::::::::::::");
		
		Map<String, Object> map = modelAndView.getModel();
		Object obj = map.get("hello");
		System.out.println(obj);
		
		if(obj!=null) {
			HttpSession session = request.getSession();
			session.setAttribute("hello", obj);
			response.sendRedirect("/board/list");
		}
		
		super.postHandle(request, response, handler, modelAndView);
	}

}
