package br.com.zupacademy.murilo.proposta.notificacao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.zupacademy.murilo.proposta.associacao.AssociacaoCartaoTask;
import br.com.zupacademy.murilo.proposta.config.validacao.exceptions.ApiErrorException;
import feign.FeignException;

@Service
public class NotificacaoBloqueioService {
	
	private Logger log = LoggerFactory.getLogger(AssociacaoCartaoTask.class);
	
	@Autowired
	private NotificacaoBloqueio notificacaoBloqueio;
	
	public void notificar(String cartaoNumero, NotificacaoForm notificacaoForm) {
		
		try {
			notificacaoBloqueio.enviar(cartaoNumero, notificacaoForm);
			
			log.info("Cartão {} bloqueado com sucesso!", cartaoNumero);
			
		} catch (FeignException e) {
			throw new ApiErrorException(HttpStatus.BAD_REQUEST, "Não foi possível bloquear o cartão de numero: " + cartaoNumero);
		}
	}
}
