package com.fiap.postechpagamentos.dominio;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "extrato_pagamento")
public class Extrato {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(nullable = false)
	private UUID pagamentoID;

	@Column(nullable = false)
	private UUID usuarioID;

	@Column(nullable = false)
	private UUID vendaID;

	@Column(nullable = false)
	private BigDecimal valorTotal;

	@Column()
	private LocalDateTime data = LocalDateTime.now();

	public Extrato(UUID pagamentoID, UUID usuarioID, UUID vendaID, BigDecimal valorTotal) {
		this.usuarioID = usuarioID;
		this.vendaID = vendaID;
		this.valorTotal = valorTotal;
		this.pagamentoID = pagamentoID;
	}

	protected Extrato() {}

	public UUID getId() {
		return id;
	}

	public UUID getUsuario() {
		return usuarioID;
	}

	public UUID getUsuarioID() {
		return usuarioID;
	}

	public UUID getVendaID() {
		return vendaID;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public UUID getPagamentoID() {
		return pagamentoID;
	}

	public LocalDateTime getData() {
		return data;
	}
}
