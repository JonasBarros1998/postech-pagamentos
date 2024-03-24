package com.fiap.postechpagamentos.view.controller;

import com.fiap.postechpagamentos.aplicacao.Pagar;
import com.fiap.postechpagamentos.view.form.PagarForm;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Service;

@Service
public class CriarPagamentoQueue {

	private Pagar pagar;

	public CriarPagamentoQueue(Pagar pagar) {
		this.pagar = pagar;
	}

	@SqsListener("gestao_de_pagamentos")
	public void pagar(PagarForm pagamentoform) {
		this.pagar.criar(pagamentoform);
	}
}
