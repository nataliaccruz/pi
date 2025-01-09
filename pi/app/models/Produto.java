package models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Produto extends Model{
	
	@Required
	public String nome;
	@Required
	public String tamanho;
	@Required
	public Double preco;
	@Required
	public String descricao;
	
	public String nomeFoto;
	
	@Enumerated(EnumType.STRING)
	public Status status;
	
	@ManyToOne
	public Compra compra;
	
	
	public Produto( ) {
		this.status = Status.ATIVO;
	}
	
}
