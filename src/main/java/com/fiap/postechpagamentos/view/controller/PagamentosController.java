package com.fiap.postechpagamentos.view.controller;

import com.fiap.postechpagamentos.aplicacao.GerenciarPagamento;
import com.fiap.postechpagamentos.view.form.ExtratoPagamentoForm;
import com.fiap.postechpagamentos.view.form.PagamentoForm;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/pagamentos")
public class PagamentosController {

	GerenciarPagamento gerenciarPagamento;

	public PagamentosController(GerenciarPagamento gerenciarPagamento) {
		this.gerenciarPagamento = gerenciarPagamento;
	}

	@PostMapping
	public ResponseEntity<PagamentoForm> adicionar(@RequestBody @Valid PagamentoForm pagamentoForm) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.gerenciarPagamento.adicionar(pagamentoForm));
	}

	@GetMapping("/{id}")
	public ResponseEntity<PagamentoForm> consultar(@PathVariable UUID id) {
		return ResponseEntity.status(HttpStatus.OK).body(this.gerenciarPagamento.consultar(id));
	}

	@GetMapping("extratos/{id}")
	public ResponseEntity<List<ExtratoPagamentoForm>> consultarExtrato(@PathVariable UUID id) {
		return ResponseEntity.status(HttpStatus.OK).body(this.gerenciarPagamento.consultarExtrato(id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable UUID id) {
		this.gerenciarPagamento.remover(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
