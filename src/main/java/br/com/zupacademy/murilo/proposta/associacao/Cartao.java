package br.com.zupacademy.murilo.proposta.associacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.murilo.proposta.bloqueio.Bloqueio;
import br.com.zupacademy.murilo.proposta.bloqueio.BloqueioRepository;
import br.com.zupacademy.murilo.proposta.bloqueio.StatusCartao;
import br.com.zupacademy.murilo.proposta.proposta.Proposta;

@Entity
public class Cartao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private LocalDateTime emitidoEm;

	@NotBlank
	private String numero;

	@NotBlank
	private String titular;

	@NotNull
	private BigDecimal limite;

	@NotNull
	@OneToOne(mappedBy = "cartao")
	private Proposta proposta;
	
	@OneToMany(mappedBy = "cartao")
	private List<Bloqueio> bloqueios;
	
	@Enumerated(EnumType.STRING)
	private StatusCartao status = StatusCartao.DESBLOQUEADO;

	@Deprecated
	public Cartao() {
	}

	public Cartao(@NotNull LocalDateTime emitidoEm, @NotBlank String numero, @NotBlank String titular,
			@NotNull BigDecimal limite, @NotNull Proposta proposta) {
		this.emitidoEm = emitidoEm;
		this.numero = numero;
		this.titular = titular;
		this.limite = limite;
		this.proposta = proposta;
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
	
	public StatusCartao getStatus() {
		return status;
	}
	
	/**
	 * Método que solicita o bloqueio do cartao.
	 * @param ip deve ser obrigatório
	 * @param userAgent deve ser obrigatório
	 * @param cartao deve ser obrigatório, deve existir e estar desbloqueado.
	 */

	public void solicitaBloqueio(String ip, String userAgent, BloqueioRepository bloqueioRepo) {
		Bloqueio bloqueio = new Bloqueio(ip, userAgent, this);
		
		bloqueioRepo.save(bloqueio);
		
		this.bloqueios.add(bloqueio);
		this.status = StatusCartao.BLOQUEADO;
	}

}