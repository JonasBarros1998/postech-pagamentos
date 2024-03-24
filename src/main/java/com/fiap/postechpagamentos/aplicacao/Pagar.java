package com.fiap.postechpagamentos.aplicacao;

import com.fiap.postechpagamentos.dominio.Extrato;
import com.fiap.postechpagamentos.infra.bancodedados.ExtratoRepository;
import com.fiap.postechpagamentos.infra.bancodedados.PagamentoRepository;
import com.fiap.postechpagamentos.view.form.PagarForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Pagar {

	ExtratoRepository extratoRepository;

	PagamentoRepository pagamentoRepository;

	@Autowired
	Pagar(ExtratoRepository extratoRepository,
	      PagamentoRepository pagamentoRepository) {
		this.extratoRepository = extratoRepository;
		this.pagamentoRepository = pagamentoRepository;
	}

	public void criar(PagarForm pagarForm) {
		this.pagamentoRepository.findById(pagarForm.pagamentoID())
			.orElseThrow(() -> new RuntimeException("O pagamento solicitado nao existe"));

		var extrato = new Extrato(
			pagarForm.pagamentoID(),
			pagarForm.usuarioID(),
			pagarForm.vendaID(),
			pagarForm.valorTotalDoCarrinho());

		this.extratoRepository.save(extrato);
	}

}
