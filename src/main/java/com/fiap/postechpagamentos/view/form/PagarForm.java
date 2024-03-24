package com.fiap.postechpagamentos.view.form;

import java.math.BigDecimal;
import java.util.UUID;

public record PagarForm(
	UUID pagamentoID,
	UUID usuarioID,
	UUID vendaID,
	BigDecimal valorTotalDoCarrinho
) {
}
