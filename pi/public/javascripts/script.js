function exibirFormulario() {
	// Ocultar todos os formulários
	document.querySelectorAll('.form-detalhe').forEach(form => form.style.display = 'none');

	// Obter o valor selecionado
	const metodo = document.getElementById('metodo').value;

	// Mostrar o formulário correspondente
	if (metodo === 'pix') {
		document.getElementById('form-pix').style.display = 'block';
	} else if (metodo === 'boleto') {
		document.getElementById('form-boleto').style.display = 'block';
	} else if (metodo === 'cartao') {
		document.getElementById('form-cartao').style.display = 'block';
	}
}


function buscarEndereco() {
    var cep = $("#cep").val();

    $.ajax({
        type: "GET",
        url: "https://viacep.com.br/ws/" + cep + "/json/",
        success: function(data) {
            if (data.erro) {
                alert("CEP não encontrado. Verifique e tente novamente.");
            } else {
                $("#cidade").val(data.localidade);
                $("#uf").val(data.uf);
                $("#endereco").focus(); // Foco no próximo campo
            }
        },
        error: function() {
            alert("Erro ao buscar o endereço. Verifique o CEP digitado.");
        }
    });
}



