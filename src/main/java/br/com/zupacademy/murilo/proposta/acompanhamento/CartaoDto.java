package br.com.zupacademy.murilo.proposta.acompanhamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.zupacademy.murilo.proposta.associacao.Cartao;

public class CartaoDto {

	private LocalDateTime emitidoEm;

	private String numero;

	private BigDecimal limite;

	public CartaoDto(Cartao cartao) {
		this.emitidoEm = cartao.getEmitidoEm();
		this.numero = cartao.getNumero();
		this.limite = cartao.getLimite();
	}

	public LocalDateTime getEmitidoEm() {
		return emitidoEm;
	}

	public String getNumero() {
		return numero;
	}

	public BigDecimal getLimite() {
		return limite;
	}

}
