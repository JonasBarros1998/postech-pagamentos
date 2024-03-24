package com.fiap.postechpagamentos.view.form;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fiap.postechpagamentos.dominio.TipoDoCartao;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record PagamentoForm(
	@Nullable
	UUID id,

	@NotNull(message = "o campo usuarioID e obrigatorio")
	UUID usuarioID,

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@NotEmpty(message = "o campo numeroDoCartao e obrigatorio")
	String numeroDoCartao,

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@NotEmpty(message = "o campo codigoDeSeguranca e obrigatorio")
	String codigoDeSeguranca,

	@NotEmpty(message = "o campo dataDeVencimento e obrigatorio")
	String dataDeVencimento,

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@NotNull(message = "o campo tipoDoCartao e obrigatorio")
	TipoDoCartao tipoDoCartao,

	@NotEmpty(message = "o campo nome e obrigatorio")
	String nome
) {
}
