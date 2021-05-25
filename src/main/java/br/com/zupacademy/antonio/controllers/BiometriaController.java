package br.com.zupacademy.antonio.controllers;

import java.net.URI;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import br.com.zupacademy.antonio.dtos.BiometriaRequest;
import br.com.zupacademy.antonio.dtos.BiometriaResponse;
import br.com.zupacademy.antonio.entities.Biometria;
import br.com.zupacademy.antonio.repositories.BiometriaRepository;
import br.com.zupacademy.antonio.repositories.CartaoRepository;

@RestController
@RequestMapping(value = "/biometria")
public class BiometriaController {

	@Autowired
	CartaoRepository cartaoRepo;
	
	@Autowired
	BiometriaRepository biometriaRepo;
	
	@PostMapping("/cartao/{cartaoId}")
	public ResponseEntity<?> cadastraBiometria(@PathVariable String cartaoId, 
			@Valid @RequestBody BiometriaRequest biometriaReq,
			UriComponentsBuilder uriBuilder){
		
		Biometria biometria =  biometriaReq.toModel(cartaoRepo, cartaoId);
		
		biometriaRepo.save(biometria);
		
		URI uri = uriBuilder.path("/cartao/{cartaoId}").buildAndExpand(biometria.getFingerprint()).toUri();
		
		return ResponseEntity.created(uri).body(new BiometriaResponse(biometria));
	}
}
