package br.com.zupacademy.antonio.dtos;

import java.util.Optional;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.zupacademy.antonio.entities.Biometria;
import br.com.zupacademy.antonio.entities.Cartao;
import br.com.zupacademy.antonio.repositories.CartaoRepository;

public class BiometriaRequest {
	
	@NotBlank
	private String fingerprint;
	
	//JSON tem problemas quando a request tem um construtor só com um parametro, por isso a anotação
	public BiometriaRequest(@JsonProperty(value = "fingerprint") String fingerprint) {
		this.fingerprint = fingerprint;
		
	}
	
	public BiometriaRequest() {}
	
	public String getFingerprint() {
		return fingerprint;
	}


	
	public Biometria toModel(CartaoRepository cartaoRepository, String cartaoId) {
		
		Optional<Cartao> cartaoFind = cartaoRepository.findById(cartaoId);
		
		Cartao cartao = cartaoFind.get();
		
		return new Biometria(fingerprint, cartao);
	}
}
