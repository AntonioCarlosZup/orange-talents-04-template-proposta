package br.com.zupacademy.antonio.feigns;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.zupacademy.antonio.dtos.AnaliseDaPropostaResponse;
import br.com.zupacademy.antonio.dtos.AnaliseDePropostaRequest;

@FeignClient(name = "solicitacao-analise", url ="${analise.financeira}")
public interface SolicitacaoAnaliseClient {

	@PostMapping("/api/solicitacao")
	AnaliseDaPropostaResponse consulta(AnaliseDePropostaRequest request);
	 
}
