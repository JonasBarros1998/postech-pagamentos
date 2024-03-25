package com.fiap.postechpagamentos.aplicacao;

import com.fiap.postechpagamentos.dominio.Pagamento;
import com.fiap.postechpagamentos.infra.bancodedados.ExtratoRepository;
import com.fiap.postechpagamentos.infra.bancodedados.PagamentoRepository;
import com.fiap.postechpagamentos.view.exceptions.PagamentoException;
import com.fiap.postechpagamentos.view.form.ExtratoPagamentoForm;
import com.fiap.postechpagamentos.view.form.PagamentoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class GerenciarPagamento {
	ExtratoRepository extratoRepository;

	PagamentoRepository pagamentoRepository;

	@Autowired
	GerenciarPagamento(ExtratoRepository extratoRepository, PagamentoRepository pagamentoRepository) {
		this.extratoRepository = extratoRepository;
		this.pagamentoRepository = pagamentoRepository;
	}

	public PagamentoForm consultar(UUID id) {
		Pagamento pagamento = this.pagamentoRepository.findByUsuarioID(id)
			.orElseThrow(() -> new PagamentoException("O pagamento consultado nao existe"));

		return new PagamentoForm(
			pagamento.getId(),
			pagamento.getUsuarioID(),
			null,
			null,
			pagamento.getDataDeVencimento(),
			null,
			pagamento.getNome()
		);
	}

	public List<ExtratoPagamentoForm> consultarExtrato(UUID id) {
		var extratos = this.extratoRepository.findAllByUsuarioID(id);

		return extratos.stream().map(item ->
			new ExtratoPagamentoForm(
				item.getUsuarioID(),
				item.getValorTotal(),
				item.getData(),
				item.getPagamentoID(),
				item.getVendaID())
		).toList();
	}

	public PagamentoForm adicionar(PagamentoForm pagamentoForm) {
		var pagamento = new Pagamento(
			pagamentoForm.usuarioID(),
			pagamentoForm.numeroDoCartao(),
			pagamentoForm.codigoDeSeguranca(),
			pagamentoForm.dataDeVencimento(),
			pagamentoForm.tipoDoCartao(),
			pagamentoForm.nome()
		);

		Pagamento pagamentoSalvo = this.pagamentoRepository.save(pagamento);

		return new PagamentoForm(
			pagamentoSalvo.getId(),
			pagamentoSalvo.getUsuarioID(),
			null,
			null,
			pagamentoSalvo.getDataDeVencimento(),
			null,
			pagamentoSalvo.getNome()
		);
	}

	public void remover(UUID id) {
		this.pagamentoRepository.deleteById(id);
	}
}
