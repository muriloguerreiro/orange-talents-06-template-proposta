package br.com.zupacademy.murilo.proposta.acompanhamento;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.murilo.proposta.proposta.Proposta;
import br.com.zupacademy.murilo.proposta.proposta.PropostaRepository;

@RestController
@RequestMapping("/propostas/{id}")
public class AcompanhamentoController {
	
	@Autowired
	private PropostaRepository propostaRepository;
	
	@GetMapping
	public ResponseEntity<AcompanhamentoDto> acompanhar(@PathVariable Long id) {
		Optional<Proposta> proposta = propostaRepository.findById(id);
		
		if (proposta.isPresent()) {
			AcompanhamentoDto acompanhamentoDto = new AcompanhamentoDto(proposta.get());
			return ResponseEntity.ok(acompanhamentoDto);
		}
		
		return ResponseEntity.notFound().build();
	}
}
