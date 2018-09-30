package br.usjt.arqsw18.pipoca.model.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.usjt.arqsw18.pipoca.model.dao.UsuarioDAO;
import br.usjt.arqsw18.pipoca.model.entity.Usuario;

@Service
public class UsuarioService {
	private UsuarioDAO dao;
	
	@Autowired
	public UsuarioService(UsuarioDAO dao) {
		this.dao = dao;
	}
	
	public boolean existe(Usuario usuario) throws IOException{
		return  dao.existe(usuario);
	}
	
	public Usuario inserirUsuario(Usuario usuario) throws IOException {
		int id = dao.inserirUsuario(usuario);
		return usuario;
	}

}
