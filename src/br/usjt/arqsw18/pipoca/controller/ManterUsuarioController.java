package br.usjt.arqsw18.pipoca.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.usjt.arqsw18.pipoca.model.entity.Usuario;
import br.usjt.arqsw18.pipoca.model.service.UsuarioService;

@Controller
public class ManterUsuarioController {
    
	private UsuarioService uService;
	
	@Autowired
	public ManterUsuarioController (UsuarioService uService) {
		this.uService = uService;
	}
	
	@RequestMapping ("/cadastro")
	public String novoUsuario() {
		return "CriarUsuario";
	}
	
	@RequestMapping ("/fazerLogin")
	public String fazerLogin (Usuario usuario, HttpServletRequest request) throws IOException {
		if(uService.existe(usuario)) {	
			System.out.println("Existe");
			request.getSession().setAttribute("usuarioLogado", usuario);
			return "index";
		}		
		
		return "Login";
	}
	
	@RequestMapping ("/logout")
	public String fazerLogin (HttpServletRequest request) throws IOException {
		request.getSession().invalidate();
		return "Login";
	}
	
	@RequestMapping ("/login")
	public String Login () {
		return "Login";
	}
	
	@RequestMapping ("/criar_usuario")
	public String criarUsuario (Usuario usuario, HttpServletRequest request) throws IOException {
		uService.inserirUsuario(usuario);
		return this.fazerLogin(usuario, request);
	}
}
