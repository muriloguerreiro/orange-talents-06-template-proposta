package br.com.zupacademy.murilo.proposta.proposta;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.murilo.proposta.solicitacao.AnaliseSolicitacaoService;
import br.com.zupacademy.murilo.proposta.solicitacao.StatusAnalise;

@RestController
@RequestMapping("/propostas")
public class PropostaController {
	
	@Autowired
	private PropostaRepository propostaRepository;
	
	@Autowired
	private AnaliseSolicitacaoService analiseService;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid CadastroPropostaForm propostaForm, UriComponentsBuilder uriBuilder) {
		Proposta proposta = propostaForm.converter();
		propostaRepository.save(proposta);
		
		StatusAnalise status = analiseService.analisar(proposta);
		proposta.atualizaStatus(status);

		propostaRepository.save(proposta);

		URI uri = uriBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}
}
