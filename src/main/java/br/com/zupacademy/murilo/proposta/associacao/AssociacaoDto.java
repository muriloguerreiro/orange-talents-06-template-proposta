package br.com.zupacademy.murilo.proposta.associacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.zupacademy.murilo.proposta.proposta.Proposta;

public class AssociacaoDto {

	private LocalDateTime emitidoEm;

	private String id;

	private String titular;

	private BigDecimal limite;

	@Deprecated
	public AssociacaoDto() {
	}

	public AssociacaoDto(LocalDateTime emitidoEm, String id, String titular, BigDecimal limite) {
		this.emitidoEm = emitidoEm;
		this.id = id;
		this.titular = titular;
		this.limite = limite;
	}

	public Cartao converter(Proposta proposta) {
		Cartao cartao = new Cartao(emitidoEm, id, titular, limite, proposta);
		return cartao;
	}

	public LocalDateTime getEmitidoEm() {
		return emitidoEm;
	}

	public String getId() {
		return id;
	}

	public String getTitular() {
		return titular;
	}

	public BigDecimal getLimite() {
		return limite;
	}

}