package br.com.zupacademy.antonio.controllers;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.antonio.dtos.BloqueioResponse;
import br.com.zupacademy.antonio.dtos.BloqueioRequest;
import br.com.zupacademy.antonio.entities.Biometria;
import br.com.zupacademy.antonio.entities.Bloqueio;
import br.com.zupacademy.antonio.entities.Cartao;
import br.com.zupacademy.antonio.enuns.Resultado;
import br.com.zupacademy.antonio.feigns.BloqueiaCartaoClient;
import br.com.zupacademy.antonio.repositories.BloqueioRepository;
import br.com.zupacademy.antonio.repositories.CartaoRepository;
import feign.FeignException;

@RestController
@RequestMapping("/bloqueios")
public class BloqueioController {

	@Autowired
	CartaoRepository cartaoRepo;

	@Autowired
	BloqueioRepository bloqueioRepo;

	@Autowired
	BloqueiaCartaoClient bloqueiaClient;

	@PostMapping("/{id}")
	public ResponseEntity<?> bloqueaCartao(@PathVariable("id") String cartaoId, @RequestBody @Valid BloqueioRequest request,
			UriComponentsBuilder uriBuilder) {
		
		Optional<Cartao> cartaoOpt = cartaoRepo.findById(cartaoId);
		
		if(cartaoOpt.isEmpty()) {
			
			return ResponseEntity.notFound().build();
		}
		
		try {
			
			BloqueioResponse bloqueioResp = bloqueiaClient.bloqueioCartao(cartaoId, request);
			
			Bloqueio bloqueio = bloqueioResp.toModel(cartaoOpt.get());
			
			bloqueioRepo.save(bloqueio);

			URI uri = uriBuilder.buildAndExpand("/bloqueios/{id}").toUri();

			return ResponseEntity.created(uri).body("Cartão Bloqueado com sucesso!");
			
		} catch (FeignException e) {
			e.printStackTrace();
			
			return ResponseEntity.unprocessableEntity().body("Ouve uma falha na hora de bloquear o cartão! "
					+ "Favor verificar se o mesmo já não está bloqueado.");
		}
		
	}

}
