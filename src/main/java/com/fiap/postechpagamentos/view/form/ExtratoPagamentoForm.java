package com.fiap.postechpagamentos.view.form;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ExtratoPagamentoForm(
	UUID usuarioID,

	BigDecimal valorTotal,

	LocalDateTime data,

	UUID pagamentoID,

	UUID vendaID
) {
}
