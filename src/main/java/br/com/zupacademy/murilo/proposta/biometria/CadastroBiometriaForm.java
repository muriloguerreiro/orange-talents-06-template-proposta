package br.com.zupacademy.murilo.proposta.biometria;

import java.util.Base64;
import java.util.Optional;

import javax.validation.constraints.NotBlank;

import org.springframework.http.HttpStatus;

import br.com.zupacademy.murilo.proposta.associacao.Cartao;
import br.com.zupacademy.murilo.proposta.config.validacao.exceptions.ApiErrorException;

public class CadastroBiometriaForm {
	
	@NotBlank
	private String fingerprint;
	
	@Deprecated
	public CadastroBiometriaForm() {
	}

	public CadastroBiometriaForm(@NotBlank String fingerprint) {
		this.fingerprint = fingerprint;
	}
	
	public String getFingerprint() {
		return fingerprint;
	}

	/**
	 * Método que converte os dados recebidos para a classe Biometria.
	 * @param encodedFingerprint é obrigatório e deve estar codificado.
	 * @param cartao é obrigatório e deve existir no banco.
	 */
	
	public Biometria converter(CartaoRepository cartaoRepository, Long cartaoId) {
		Optional<Cartao> buscaCartao = cartaoRepository.findById(cartaoId);
		
		if (buscaCartao.isEmpty()) {
			throw new ApiErrorException(HttpStatus.NOT_FOUND, "Cartão não encontrado");
		}
		
		Cartao cartao = buscaCartao.get();
		String encodedFingerprint = Base64.getEncoder().encodeToString(fingerprint.getBytes());
		
		return new Biometria(encodedFingerprint, cartao);
	}
}
