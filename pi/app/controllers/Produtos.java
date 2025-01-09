package controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import models.Produto;
import models.Status;
import play.cache.Cache;
import play.mvc.Controller;
import play.mvc.With;

@With(Seguranca.class)
public class Produtos extends Controller {

	public static void form() {
		Produto produto = (Produto) Cache.get("Produto");
		Cache.clear();
		render(produto);
	}

	public static void principal(String termo) {
		List<Produto> produtos = null;
		if (termo == null) {
			produtos = Produto.find("status = ?1", Status.ATIVO).fetch();
		} else {
			produtos = Produto.find("(lower(nome) like ?1 or email like ?1 or descricao like ?1) AND status = ?2",
					"%" + termo.toLowerCase() + "%", Status.ATIVO).fetch();
		}
		render(produtos, termo);
	}

	public static void detalhar(Produto produto) {
		render(produto);
	}

	public static void salvar(@Valid Produto produto, File foto) {
			
		if (validation.hasErrors()) {
			validation.keep();

			flash.error("Falha ao salvar o novo produto!");
			Cache.set("produto", produto);

			form();
		}
		
		produto.nomeFoto = foto.getName();
		produto.save();
		
		new File(".\\pi\\\\uploads\\" + produto.id).mkdirs();
		
		File dest = new File(".\\pi\\uploads\\" + produto.id + "\\" + foto.getName());
		if (dest.exists()){
			dest.delete();
		}
		
		foto.renameTo(dest);

		
		flash.success("Produto cadastrado com sucesso!");
		principal(null);
	}

	public static void editar(Long id) {
		Produto p = Produto.findById(id);
		renderTemplate("Produtos/form.html", p);
	}

	public static void remover(Long id) {
		Produto p = Produto.findById(id);
		p.status = Status.INATIVO;
		p.save();
		principal(null);
	}

	public static void adicionarSacola(Long idProduto) {
		Produto produto = Produto.findById(idProduto);
		if (produto == null) {
			notFound("Produto não encontrado.");
		}

		List<Produto> sacola = (List<Produto>) Cache.get(session.getId() + "-sacola");
		if (sacola == null) {
			sacola = new ArrayList<>();
		}

		sacola.add(produto);
		Cache.set(session.getId() + "-sacola", sacola);
		principal(null);
	}

	public static void exibirSacola() {
		List<Produto> sacola = (List<Produto>) Cache.get(session.getId() + "-sacola");
		if (sacola == null) {
			sacola = new ArrayList<>();
		}

		double total = 0;
		for (Produto produto : sacola) {
			total += produto.preco;
		}

		render(sacola, total);
	}

	public static void removerSacola(Long idProduto) {
		List<Produto> sacola = (List<Produto>) Cache.get(session.getId() + "-sacola");
		if (sacola != null) {
			sacola.removeIf(p -> p.id.equals(idProduto));
			Cache.set(session.getId() + "-sacola", sacola);
		}
		exibirSacola();
	}

}
