package jobs;

import models.Produto;
import models.Usuario;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
public class Inicializador extends Job {

	@Override
	public void doJob() throws Exception {
		if (Produto.count() == 0) {
			Produto c1 = new Produto();
			c1.nome = "Vestido Floral Alça Pérola Ceren / Preto";
			c1.tamanho = "M";
			c1.preco = (double) 279.89;
			c1.descricao = "Eleve seu fascínio com este vestido longo e justo, adornado com alças de pérolas e detalhes florais.";
			c1.nomeFoto = "../1/roupa3.jpg";
			c1.save();

			Produto c2 = new Produto();
			c2.nome = "Vestido Verde Cetim Com Folho Miss You";
			c2.tamanho = "G";
			c2.preco = (double) 79.99;
			c2.descricao = "Vestido na cor verde em cetim com folho . Manga curta e racha lateral. 100% Poliéster";
			c2.nomeFoto = "../2/roupa4.jpg";
			c2.save();
		}

		if (Usuario.count() == 0) {
			Usuario jami = new Usuario();
			jami.nome = "Jamily Quirino";
			jami.usuario = "@jami";
			jami.email = "jamily@gmail.com";
			jami.senha = "1234";
			jami.perfil = "admin";
			jami.cep = "59594000";
			jami.uf = "RN";
			jami.cidade = "Jandaíra";
			jami.endereco = "Rua das palmeiras";
			jami.numeroCasa = "25";
			jami.save();

			Usuario nat = new Usuario();
			nat.nome = "Natalia Carvalho";
			nat.usuario = "@nat";
			nat.email = "natalia@gmail.com";
			nat.senha = "12345";
			nat.perfil = "admin";
			jami.cep = "59560000";
			jami.uf = "RN";
			jami.cidade = "Jandaíra";
			jami.endereco = "Rua das palmeiras";
			jami.numeroCasa = "25";
			nat.save();

			Usuario ota = new Usuario();
			ota.nome = "Otavio Felipe";
			ota.usuario = "@ota";
			ota.email = "otavio@gmail.com";
			ota.senha = "123456";
			ota.perfil = "admin";
			ota.cep = "59594000";
			ota.uf = "RN";
			ota.cidade = "Jandaíra";
			ota.endereco = "Beco do bujão";
			ota.numeroCasa = "24";
			ota.save();

			Usuario ana = new Usuario();
			ana.nome = "Ana Clara";
			ana.usuario = "@ana";
			ana.email = "ana@gmail.com";
			ana.senha = "123";
			ana.perfil = "cliente";
			ana.cep = "59594000";
			ana.uf = "RN";
			ana.cidade = "Jandaíra";
			ana.endereco = "Rua das palmeiras";
			ana.numeroCasa = "25";
			ana.save();
		}

	}

}
