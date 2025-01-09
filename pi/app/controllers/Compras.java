package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Compra;
import models.Produto;
import models.Status;
import models.Usuario;
import play.cache.Cache;
import play.mvc.Controller;
import play.mvc.With;

@With(Seguranca.class)
public class Compras extends Controller {

	public static void form() {
		List<Produto> sacola = (List<Produto>) Cache.get(session.getId() + "-sacola");
		if (sacola == null) {
			sacola = new ArrayList<>();
		}

		double total = calcularValorTotal(sacola); 
		render(total);
	}

	public static void salvar(Compra compra, Usuario usu) {
	    List<Produto> sacola = (List<Produto>) Cache.get(session.getId() + "-sacola");
	    if (sacola == null) {
	        sacola = new ArrayList<>();
	    }

	    double total = calcularValorTotal(sacola);
	    compra.valorTotal = total; 

	    String emailLogado = session.get("email");
	    compra.email = emailLogado;

	    compra.save();
	    Cache.set(session.getId() + "-sacola", null);
	    Produtos.principal(null);
	}


	public static void listar(String termo) {
		List<Compra> compras;
		if (termo == null || termo.isEmpty()) {
			compras = Compra.findAll();
		} else {
			compras = Compra.find("(lower(email) like ?1 OR lower(endereco) like ?1)", "%" + termo.toLowerCase() + "%")
					.fetch();
		}
		render(compras, termo);
	}

	public static void remover(Long id) {
		Compra c = Compra.findById(id);
		c.status = Status.INATIVO;
		c.save();
		listar(null);
	}

	public static double calcularValorTotal(List<Produto> produtos) {
		double total = 0;
		for (Produto produto : produtos) {
			total += produto.preco;
		}

		return total;
	}

}
