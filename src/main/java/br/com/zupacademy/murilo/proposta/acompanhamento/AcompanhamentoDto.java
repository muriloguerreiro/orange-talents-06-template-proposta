package br.com.zupacademy.murilo.proposta.acompanhamento;

import java.math.BigDecimal;

import br.com.zupacademy.murilo.proposta.proposta.Proposta;
import br.com.zupacademy.murilo.proposta.solicitacao.StatusProposta;

public class AcompanhamentoDto {

	private String documento;

	private String email;

	private String nome;

	private String endereco;

	private BigDecimal salario;

	private StatusProposta status;

	private CartaoDto cartao;

	public AcompanhamentoDto(Proposta proposta) {
		this.documento = proposta.getDocumento();
		this.email = proposta.getEmail();
		this.nome = proposta.getNome();
		this.endereco = proposta.getEndereco();
		this.salario = proposta.getSalario();
		this.status = proposta.getStatus();
		if(proposta.getCartao() != null) this.cartao = new CartaoDto(proposta.getCartao());
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

	public StatusProposta getStatus() {
		return status;
	}

	public CartaoDto getCartao() {
		return cartao;
	}

}
