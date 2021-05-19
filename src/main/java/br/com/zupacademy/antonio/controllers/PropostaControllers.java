package br.com.zupacademy.antonio.controllers;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.antonio.dtos.AnaliseDaPropostaResponse;
import br.com.zupacademy.antonio.dtos.AnaliseDePropostaRequest;
import br.com.zupacademy.antonio.dtos.PropostaResponse;
import br.com.zupacademy.antonio.dtos.PropostaRequest;
import br.com.zupacademy.antonio.entities.Proposta;
import br.com.zupacademy.antonio.enuns.Status;
import br.com.zupacademy.antonio.feigns.SolicitacaoAnaliseClient;
import br.com.zupacademy.antonio.repositories.PropostaRepository;
import br.com.zupacademy.antonio.schedules.AssociaCartaoSchedule;
import feign.FeignException;

@RestController
@RequestMapping(value = "/proposta")
public class PropostaControllers {

	@Autowired
	PropostaRepository propRepository;

	@Autowired
	SolicitacaoAnaliseClient analiseClient;
	
	@Autowired
	AssociaCartaoSchedule associaCartao;

	@PostMapping
	// @Transactional - tentar evitar usar pois transforma todo o método em uma
	// transação
	public ResponseEntity<?> criarProposta(@RequestBody @Valid PropostaRequest request, UriComponentsBuilder uriBuilder) {

		Optional<Proposta> propostaOpt = propRepository.findByCpfCnpj(request.getCpfCnpj());

		if (propostaOpt.isPresent()) {

			return ResponseEntity.unprocessableEntity().build();

		} else {
			Proposta proposta = request.converter();

			propRepository.save(proposta);


			try {
				

				AnaliseDePropostaRequest analiseRequest = 
						new AnaliseDePropostaRequest(
						proposta.getCpfCnpj(),
						proposta.getNome(), 
						proposta.getId());
				
				AnaliseDaPropostaResponse resultadoDaConsulta = 
						analiseClient.consulta(analiseRequest);

				Status status = resultadoDaConsulta.status();

				proposta.setStatus(status);

			} catch (FeignException.UnprocessableEntity unprocessableEntity) {
				
				proposta.setStatus(Status.NAO_ELEGIVEL);
				
			}

			propRepository.save(proposta);

			URI uri = uriBuilder.path("/proposta/{id}")
					.buildAndExpand(proposta.getId()).toUri();
			
			associaCartao.VinculoCartaoProposta();
			
			return ResponseEntity.created(uri).body(new PropostaResponse(proposta));
		}

	}

}
