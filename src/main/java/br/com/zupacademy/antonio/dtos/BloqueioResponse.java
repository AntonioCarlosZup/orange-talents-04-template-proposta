package br.com.zupacademy.antonio.dtos;

import br.com.zupacademy.antonio.entities.Bloqueio;
import br.com.zupacademy.antonio.entities.Cartao;
import br.com.zupacademy.antonio.enuns.Resultado;

public class BloqueioResponse {

	private Resultado resultado;
	private String cartao;
	
	/*public BloqueioResponse(Bloqueio bloqueio) {
		this.id = bloqueio.getId();
		this.resultado = bloqueio.getResultado();
		this.cartao = bloqueio.getCartao().getId();
	
	
	}
	 */
	
	public Resultado getResultado() {
		return resultado;
	}

	public String getCartao() {
		return cartao;
	}
	
	public Bloqueio toModel(Cartao cartao) {
		
		return new Bloqueio(resultado, cartao);
	}
}
