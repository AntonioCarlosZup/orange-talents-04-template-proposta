package br.com.zupacademy.antonio.dtos;

import br.com.zupacademy.antonio.entities.Cartao;
import br.com.zupacademy.antonio.enuns.ResultadoSolicitacao;
import br.com.zupacademy.antonio.enuns.Status;

public class AnaliseDaPropostaResponse {

	private String documento;
	private String nome;
	private String idProposta;
	private ResultadoSolicitacao resultadoSolicitacao;
	private Cartao cartao;
	
	public String getDocumento() {
		return documento;
	}
	public String getNome() {
		return nome;
	}
	public String getIdProposta() {
		return idProposta;
	}
	public ResultadoSolicitacao getResultadoSolicitacao() {
		return resultadoSolicitacao;
	}
	public Status status() {
		
		return resultadoSolicitacao.getStatus();		
	}
	public Cartao getCartao() {
		return cartao;
	}
	
}
