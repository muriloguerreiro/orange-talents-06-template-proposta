package br.com.zupacademy.murilo.proposta.biometria;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/biometrias")
public class BiometriaController {
	
	@Autowired
	private BiometriaRepository biometriaRepository;
	
	@Autowired
	private CartaoRepository cartaoRepository;

	@PostMapping("/cartaoId={id}")
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid CadastroBiometriaForm biometriaForm, @PathVariable Long id, UriComponentsBuilder uriBuilder) {
		
		Biometria biometria = biometriaForm.converter(cartaoRepository, id);
		biometriaRepository.save(biometria);

		URI uri = uriBuilder.path("/biometrias/{id}").buildAndExpand(biometria.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}
	
}
