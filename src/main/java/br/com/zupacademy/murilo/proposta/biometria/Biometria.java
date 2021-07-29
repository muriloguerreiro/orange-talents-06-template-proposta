package br.com.zupacademy.murilo.proposta.biometria;

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
public class Biometria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String fingerprint;
	
	@NotNull
	private LocalDateTime dataCriacao;
	
	@NotNull
	@ManyToOne
	private Cartao cartao;
	
	@Deprecated
	public Biometria() {
	}

	public Biometria(@NotBlank String fingerprint, @NotNull Cartao cartao) {
		this.fingerprint = fingerprint;
		this.dataCriacao = LocalDateTime.now();
		this.cartao = cartao;
	}

	public Long getId() {
		return id;
	}
	
}
