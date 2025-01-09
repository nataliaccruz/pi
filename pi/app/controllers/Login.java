package controllers;

import models.Usuario;
import play.libs.Crypto;
import play.mvc.Controller;

public class Login extends Controller{
	
	public static void form() {
		render();
	}
	
	public static void logar(String usuario, String senha, String email) {
		Usuario usu = Usuario.find("usuario = ?1 and senha = ?2", usuario, senha ).first();
		
		if (usu != null) {
			session.put("user", usu.usuario);
			session.put("perfil", usu.perfil);
			session.put("email", usu.email);
			session.put("nome", usu.nome);
			
			Produtos.principal(null);

		} else {
			flash.error("Login ou senha inválidos");
			form();

		}
	}
	
	public static void sair() {
		session.clear();
		Produtos.principal(null);
	}

}
