package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Compra extends Model{
	
	public String email;
	public String metodo;
	public String cpf;
	public Double valorTotal;
	
	@OneToMany(mappedBy = "compra")
	public List<Produto> produto;
	
	@Enumerated(EnumType.STRING)
	public Status status;
	
	public Compra( ) {
		this.status = Status.ATIVO;
	}


}
