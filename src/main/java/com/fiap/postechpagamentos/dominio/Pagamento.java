package com.fiap.postechpagamentos.dominio;

import com.fiap.postechpagamentos.infra.criptografia.ConverterDados;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "pagamento")
public class Pagamento {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(nullable = false)
	private UUID usuarioID;

	@Convert(converter = ConverterDados.class)
	@Column(nullable = false)
	private String numeroDoCartao;

	@Convert(converter = ConverterDados.class)
	@Column(nullable = false)
	private String codigoDeSeguranca;

	@Column(nullable = false)
	private String dataDeVencimento;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, name = "tipo_do_cartao")
	private TipoDoCartao tipoDoCartao;

	@Convert(converter = ConverterDados.class)
	@Column(nullable = false)
	private String nome;

	protected Pagamento() {}

	public Pagamento(UUID usuarioID, String numeroDoCartao, String codigoDeSeguranca, String dataDeVencimento, TipoDoCartao tipoDoCartao, String nome) {
		this.usuarioID = usuarioID;
		this.numeroDoCartao = numeroDoCartao;
		this.codigoDeSeguranca = codigoDeSeguranca;
		this.dataDeVencimento = dataDeVencimento;
		this.tipoDoCartao = tipoDoCartao;
		this.nome = nome;
	}

	public UUID getId() {
		return id;
	}

	public String getNumeroDoCartao() {
		return numeroDoCartao;
	}

	public String getCodigoDeSeguranca() {
		return codigoDeSeguranca;
	}

	public String getDataDeVencimento() {
		return dataDeVencimento;
	}

	public TipoDoCartao getTipoDoCartao() {
		return tipoDoCartao;
	}

	public String getNome() {
		return nome;
	}

	public UUID getUsuarioID() {
		return usuarioID;
	}
}
