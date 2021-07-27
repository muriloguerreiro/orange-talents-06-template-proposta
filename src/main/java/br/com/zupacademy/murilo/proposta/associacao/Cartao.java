package br.com.zupacademy.murilo.proposta.associacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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

}
