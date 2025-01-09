package controllers;

import play.mvc.Before;
import play.mvc.Controller;

public class Seguranca extends Controller{
	
	@Before(unless={"Produtos.principal"})
	static void verificar() {
		if (session.get("user") == null) {
			Login.form();
		}
	}
}

