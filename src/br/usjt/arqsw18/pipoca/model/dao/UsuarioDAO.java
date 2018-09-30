package br.usjt.arqsw18.pipoca.model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.usjt.arqsw18.pipoca.model.entity.Usuario;

@Repository
public class UsuarioDAO {
	
	private Connection conn;
	
	@Autowired
	public UsuarioDAO(DataSource dataSource) throws IOException {
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			throw new IOException (e);
		}
	}
	
	public boolean existe (Usuario usuario) throws IOException {
		String sql = "select login, senha from usuario where login = ? and senha = ? ";
		
		try(PreparedStatement pst = conn.prepareStatement(sql);){
			
			pst.setString(1, usuario.getLogin());
			pst.setString(2, usuario.getSenha());
		
			try(ResultSet rs = pst.executeQuery();){
				if(rs.next()) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
				
		
		return false;
	}

	public int inserirUsuario(Usuario usuario) throws IOException {
		int id = -1;
		String sql = "insert into Usuario (login, senha) values (?,?)";
		
		try(PreparedStatement pst = conn.prepareStatement(sql);){
			
			pst.setString(1, usuario.getLogin());
			pst.setString(2, usuario.getSenha());
			pst.execute();
			
			//obter o id criado
			String query = "select LAST_INSERT_ID()";
			try(PreparedStatement pst1 = conn.prepareStatement(query);
				ResultSet rs = pst1.executeQuery();){

				if (rs.next()) {
					id = rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		return id;
	}

}
