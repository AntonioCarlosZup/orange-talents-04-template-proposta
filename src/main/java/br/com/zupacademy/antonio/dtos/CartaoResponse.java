package br.com.zupacademy.antonio.dtos;

import java.time.LocalDateTime;

import br.com.zupacademy.antonio.entities.Cartao;
import br.com.zupacademy.antonio.entities.Proposta;

public class CartaoResponse {

	private String id;
	private LocalDateTime emitidoEm;
	private String titular;
	private Proposta proposta;
	
	
	@Deprecated
	public CartaoResponse() {}

	public String getId() {
		return id;
	}

	public LocalDateTime getEmitidoEm() {
		return emitidoEm;
	}

	public String getTitular() {
		return titular;
	}

	public Proposta GetProposta() {
		return proposta;
	}
	
	public Cartao toModel(Proposta proposta) {	
		
		return new Cartao(id, emitidoEm, titular, proposta);
		
	}
	
}
