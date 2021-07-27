package br.com.zupacademy.murilo.proposta.associacao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.murilo.proposta.config.validacao.anotacoes.CpfCnpj;

public class AssociacaoForm {

	@NotBlank
	@CpfCnpj
	private String documento;

	@NotBlank
	private String nome;

	@NotNull
	private Long idProposta;

	public AssociacaoForm(@NotBlank String documento, @NotBlank String nome, @NotNull Long idProposta) {
		this.documento = documento;
		this.nome = nome;
		this.idProposta = idProposta;
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public Long getIdProposta() {
		return idProposta;
	}

}
