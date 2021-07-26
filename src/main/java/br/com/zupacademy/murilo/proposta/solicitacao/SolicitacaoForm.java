package br.com.zupacademy.murilo.proposta.solicitacao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.murilo.proposta.config.validacao.anotacoes.CpfCnpj;
import br.com.zupacademy.murilo.proposta.proposta.Proposta;

public class SolicitacaoForm {

	@NotBlank
	@CpfCnpj
	private String documento;

	@NotBlank
	private String nome;

	@NotNull
	private Long idProposta;

	public SolicitacaoForm(Proposta proposta) {
		this.documento = proposta.getDocumento();
		this.nome = proposta.getNome();
		this.idProposta = proposta.getId();
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
