package br.usjt.arqsw18.pipoca.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

	public class AutorizadorInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response,
			Object controller) throws Exception {
		
		String uri = request.getRequestURI();
		
		if(uri.endsWith("login") || uri.endsWith("fazerLogin") ||
				uri.contains("css") || uri.contains("js") ||
				uri.contains("img") || uri.endsWith("cadastro") || uri.endsWith("criar_usuario")){
				return true;
		}
		
		if(request.getSession().getAttribute("usuarioLogado") != null) {
			return true;
		}
		
		response.sendRedirect("login");
		return false;
	}
}