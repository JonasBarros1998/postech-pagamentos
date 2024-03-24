package com.fiap.postechpagamentos.dominio;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum TipoDoCartao {

	Credito("CREDITO"),
	Debito("DEBITO");

	private final String tipoDoCartao;

	TipoDoCartao(String tipoDoCartao) {
		this.tipoDoCartao = tipoDoCartao;
	}

	@JsonCreator
	public static TipoDoCartao decode(final String tipo) {
		return Stream.of(TipoDoCartao.values())
			.filter(targetEnum -> targetEnum.tipoDoCartao.equals(tipo))
			.findFirst()
			.orElseThrow();
	}

	@JsonValue
	public String getCode() {
		return tipoDoCartao;
	}

}
