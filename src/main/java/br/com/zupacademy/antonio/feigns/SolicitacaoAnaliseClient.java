package br.com.zupacademy.antonio.feigns;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.zupacademy.antonio.dtos.AnaliseDaPropostaResponse;
import br.com.zupacademy.antonio.dtos.AnaliseDePropostaRequest;

@FeignClient(name = "solicitacao-analise", url = "http://localhost:9999")
public interface SolicitacaoAnaliseClient {

	@PostMapping("/api/solicitacao")
	AnaliseDaPropostaResponse consulta(AnaliseDePropostaRequest request);
	 
}
