package br.com.zupacademy.murilo.proposta.bloqueio;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.murilo.proposta.associacao.Cartao;

@Entity
public class Bloqueio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private LocalDateTime instante;

	@NotBlank
	private String ip;

	@NotBlank
	private String userAgent;
	
	@ManyToOne
	private Cartao cartao;
	
	@Deprecated
	public Bloqueio() {
	}

	public Bloqueio(@NotBlank String ip, @NotBlank String userAgent, Cartao cartao) {
		this.instante = LocalDateTime.now();
		this.ip = ip;
		this.userAgent = userAgent;
		this.cartao = cartao;
	}
	
}
