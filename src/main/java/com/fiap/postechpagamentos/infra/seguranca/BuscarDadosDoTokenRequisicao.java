package com.fiap.postechpagamentos.infra.seguranca;

import com.fiap.postechpagamentos.view.exceptions.TokenException;
import org.springframework.stereotype.Service;

@Service
public class BuscarDadosDoTokenRequisicao {

	BuscarDadosDoToken buscarDadosDoToken;

	//@Autowired
	BuscarDadosDoTokenRequisicao(BuscarDadosDoToken buscarDadosDoToken) {
		this.buscarDadosDoToken = buscarDadosDoToken;
	}

	public Usuario buscar(String token) {
		var resposta = this.buscarDadosDoToken.buscar(token);
		if(resposta.getStatusCode().is2xxSuccessful()) {
			return resposta.getBody();
		}

		throw new TokenException("Token invalido");
	}
}