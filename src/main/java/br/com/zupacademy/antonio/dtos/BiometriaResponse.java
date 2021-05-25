package br.com.zupacademy.antonio.dtos;

import br.com.zupacademy.antonio.entities.Biometria;

public class BiometriaResponse {

	private String fingerprint;
	private String cartao;
	
	public BiometriaResponse(Biometria biometria) {
		this.fingerprint = biometria.getFingerprint();
		this.cartao = biometria.getCartao().getId();
		
	}
	
	public String getFingerprint() {
		return fingerprint;
	}
	public String getCartao() {
		return cartao;
	}
	
}
