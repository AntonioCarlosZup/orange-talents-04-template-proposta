package br.com.zupacademy.antonio.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Cartao {

	@Id
	private String id;
	private LocalDateTime emitidoEm;
	private String titular;
	
	@OneToMany(mappedBy = "cartao")
	private List<Biometria> biometria; 
	
	@OneToOne(mappedBy = "cartao", fetch = FetchType.LAZY)
	private Proposta proposta;
	
	public Cartao(String id, LocalDateTime emitidoEm, String titular, Proposta proposta) {
		super();
		this.id = id;
		this.emitidoEm = emitidoEm;
		this.titular = titular;
		this.proposta = proposta;
		
	}
		
	@Deprecated
	public Cartao() {}
		
	
	public String getId() {
		return id;
	}
	public LocalDateTime getEmitidoEm() {
		return emitidoEm;
	}
	public String getTitular() {
		return titular;
	}

	public Proposta getProposta() {
		return proposta;
	}

}
