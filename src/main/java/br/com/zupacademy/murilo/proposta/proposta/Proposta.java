package br.com.zupacademy.murilo.proposta.proposta;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.util.Assert;

import br.com.zupacademy.murilo.proposta.associacao.AssociacaoDto;
import br.com.zupacademy.murilo.proposta.associacao.Cartao;
import br.com.zupacademy.murilo.proposta.config.validacao.anotacoes.CpfCnpj;
import br.com.zupacademy.murilo.proposta.solicitacao.StatusAnalise;
import br.com.zupacademy.murilo.proposta.solicitacao.StatusProposta;

@Entity
public class Proposta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@CpfCnpj
	private String documento;

	@NotBlank
	@Email
	private String email;

	@NotBlank
	private String nome;

	@NotBlank
	private String endereco;

	@NotNull
	@PositiveOrZero
	private BigDecimal salario;
	
	@Enumerated(EnumType.STRING)
	private StatusProposta status;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	private Cartao cartao;

	@Deprecated
	public Proposta() {
	}

	public Proposta(@NotBlank String documento, @NotBlank @Email String email, @NotBlank String nome,
			@NotBlank String endereco, @NotNull @PositiveOrZero BigDecimal salario) {
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}

	public Long getId() {
		return id;
	}
	
	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}
	
	public String getEmail() {
		return email;
	}

	public String getEndereco() {
		return endereco;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public StatusProposta getStatus() {
		return status;
	}

	public Cartao getCartao() {
		return cartao;
	}

	/**
	 * Método que atualiza o status da Proposta após chamar o serviço de analise.
	 * @param statusAnalise não pode ser nulo
	 */
	
	public void atualizaStatus(StatusAnalise statusAnalise) {
		Assert.notNull(statusAnalise, "O status recebido não pode ser nulo");
		
		if (statusAnalise == StatusAnalise.SEM_RESTRICAO) {
			this.status = StatusProposta.ELEGIVEL;
		} else if (statusAnalise == StatusAnalise.COM_RESTRICAO) {
			this.status = StatusProposta.NAO_ELEGIVEL;
		}

	}
	
	/**
	 * Método que associa o cartao a Proposta após chamar o serviço de associacao.
	 * @param associacaoDto deve ser um objeto não nulo recebido do serviço externo
	 */

	public void associaCartao(AssociacaoDto associacaoDto) {
		Cartao cartao = associacaoDto.converter(this);
		this.cartao = cartao;
	}

}
