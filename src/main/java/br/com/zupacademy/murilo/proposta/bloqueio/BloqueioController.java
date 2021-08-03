package br.com.zupacademy.murilo.proposta.bloqueio;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.murilo.proposta.associacao.Cartao;
import br.com.zupacademy.murilo.proposta.biometria.CartaoRepository;
import br.com.zupacademy.murilo.proposta.config.validacao.exceptions.ApiErrorException;
import br.com.zupacademy.murilo.proposta.notificacao.NotificacaoBloqueioService;
import br.com.zupacademy.murilo.proposta.notificacao.NotificacaoForm;


@RestController
@RequestMapping("/bloqueio")
public class BloqueioController {
	
	@Autowired
	private CartaoRepository cartaoRepository;
	
	@Autowired
	private BloqueioRepository bloqueioRepository;
	
	@Autowired
	private NotificacaoBloqueioService notificacaoService;

	@PostMapping("/cartaoId={id}")
	@Transactional
	public ResponseEntity<?> solicitar(HttpServletRequest request, @PathVariable Long id) {
		
		String ip = request.getRemoteAddr();
		String userAgent = request.getHeader(HttpHeaders.USER_AGENT);
		
		Optional<Cartao> buscaCartao = cartaoRepository.findById(id);
		
		if (buscaCartao.isEmpty()) {
			throw new ApiErrorException(HttpStatus.NOT_FOUND, "Cartão não encontrado");
		}
		
		Cartao cartao = buscaCartao.get();
		
		if (cartao.getStatus() == StatusCartao.BLOQUEADO) {
			throw new ApiErrorException(HttpStatus.UNPROCESSABLE_ENTITY, "Cartão já está bloqueado");
		}
		
		NotificacaoForm notificacaoForm = new NotificacaoForm("API de Proposta");
		notificacaoService.notificar(cartao.getNumero(),notificacaoForm);
		
		cartao.solicitaBloqueio(ip,userAgent,bloqueioRepository);
		cartaoRepository.save(cartao);

		return ResponseEntity.ok().build();
	}
	
}