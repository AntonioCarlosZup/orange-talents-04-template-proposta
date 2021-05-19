package br.com.zupacademy.antonio.entities;

public class AnaliseDePropostaRequest {

	private String documento;
	private String nome;
	private Long idProposta;
	
	@Deprecated
	public AnaliseDePropostaRequest() {}
	
	public AnaliseDePropostaRequest(String documento, String nome, Long idProposta) {
		this.documento = documento;
		this.nome = nome;
		this.idProposta = idProposta;
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public Long getIdProposta() {
		return idProposta;
	}
	
}
