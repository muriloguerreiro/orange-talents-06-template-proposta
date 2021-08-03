package br.com.zupacademy.murilo.proposta.proposta;

import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import br.com.zupacademy.murilo.proposta.config.validacao.anotacoes.CpfCnpj;
import br.com.zupacademy.murilo.proposta.config.validacao.anotacoes.ValorUnico;

public class CadastroPropostaForm {
	
	@NotBlank 
	@CpfCnpj
	@ValorUnico(domainClass = Proposta.class, fieldName = "documento")
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
    
    @Deprecated
	public CadastroPropostaForm() {
	}

	public CadastroPropostaForm(@NotBlank String documento, @NotBlank @Email String email, @NotBlank String nome,
			@NotBlank String endereco, @NotNull @PositiveOrZero BigDecimal salario) {
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}
	
	public String getDocumento() {
		return documento;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	/**
	 * Método que converte os dados recebidos para a classe Proposta.
	 * @param documento é obrigatório e deve ser um CPF ou CNPJ válido.
	 * @param email é obrigatório e deve estar no formato email.
	 * @param nome é obrigatório
	 * @param endereco é obrigatório
	 * @param salario é obrigatório e não pode ser negativo
	 */
	
	public Proposta converter() {
		return new Proposta(documento, email, nome, endereco, salario);
	}
    
}
