package com.fiap.postechpagamentos.infra.seguranca;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class Autenticar {

	private BuscarDadosDoTokenRequisicao buscarDadosDoTokenRequisicao;
	Autenticar(BuscarDadosDoTokenRequisicao buscarDadosDoTokenRequisicao) {
		this.buscarDadosDoTokenRequisicao = buscarDadosDoTokenRequisicao;
	}

	public Usuario usuario(HttpServletRequest requisicao) {
		var token = Token.recuperar(requisicao);
		return this.buscarDadosDoTokenRequisicao.buscar(token);
	}
}