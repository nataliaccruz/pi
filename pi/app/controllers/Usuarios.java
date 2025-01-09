package controllers;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.annotations.Cascade;

import models.Usuario;
import play.cache.Cache;
import play.mvc.Controller;
import play.mvc.With;

public class Usuarios extends Controller {

	public static void form() {
		Usuario usu = (Usuario) Cache.get("usu");
		Cache.clear();
		render(usu);
	}

	public static void salvar(Usuario usu, String senha) {

		if (senha.equals("") == false || usu.id == null) {
			usu.senha = senha;

			if (senha.length() < 6) {
				validation.addError("usu.senha", "A senha deve ter no mínimo 6 digitos");
			}

		}

		if (usu.perfil == null) {
			usu.perfil = "cliente";
		}

		if (!usu.usuario.contains("@")) {
			usu.usuario = "@" + usu.usuario;
		}

		usu.save();
		flash.success("sucesso");
		Login.form();
	}

	public static void editar(Long id) {
		Usuario usu = Usuario.findById(id);
		renderTemplate("Usuarios/form.html", usu);

	}

	public static void remover(Long id) {
		Usuario usu = Usuario.findById(id);
		usu.delete();
	}

}
