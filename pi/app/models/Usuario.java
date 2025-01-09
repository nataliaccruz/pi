package models;

import javax.persistence.Entity;
import javax.persistence.Transient;

import play.data.validation.Email;
import play.data.validation.Equals;
import play.data.validation.Required;
import play.db.jpa.Model;
import play.libs.Crypto;

@Entity
public class Usuario extends Model {

	@Required
	public String nome;
	@Required
	public String usuario;

	@Required
	@Email
	public String email;

	@Required
	public String senha;

	@Required
	public String perfil;

	@Required
	public String cep;
	@Required
	public String uf;
	@Required
	public String cidade;
	@Required
	public String endereco;
	@Required
	public String numeroCasa;

}
