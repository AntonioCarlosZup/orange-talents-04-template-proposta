package br.com.zupacademy.antonio.controllers;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.antonio.dtos.PropostaDto;
import br.com.zupacademy.antonio.dtos.PropostaForm;
import br.com.zupacademy.antonio.entities.AnaliseDaPropostaResponse;
import br.com.zupacademy.antonio.entities.AnaliseDePropostaRequest;
import br.com.zupacademy.antonio.entities.Proposta;
import br.com.zupacademy.antonio.enuns.Status;
import br.com.zupacademy.antonio.feigns.SolicitacaoAnaliseClient;
import br.com.zupacademy.antonio.repositories.PropostaRepository;
import feign.FeignException;

@RestController
@RequestMapping(value = "/proposta")
public class PropostaControllers {

	@Autowired
	PropostaRepository propRepository;

	@Autowired
	SolicitacaoAnaliseClient analiseClient;

	@PostMapping
	// @Transactional - tentar evitar usar pois transforma todo o método em uma
	// transação
	public ResponseEntity<?> criarProposta(@RequestBody @Valid PropostaForm request, UriComponentsBuilder uriBuilder) {

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

			return ResponseEntity.created(uri).body(new PropostaDto(proposta));
		}

	}

}
