package br.com.zupacademy.antonio.schedules;

import java.util.List;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.zupacademy.antonio.dtos.CartaoResponse;
import br.com.zupacademy.antonio.entities.Proposta;
import br.com.zupacademy.antonio.enuns.Status;
import br.com.zupacademy.antonio.feigns.VinculaCartaoClient;
import br.com.zupacademy.antonio.repositories.CartaoRepository;
import br.com.zupacademy.antonio.repositories.PropostaRepository;
import feign.FeignException;

@Component
public class AssociaCartaoSchedule {

	@Autowired
	PropostaRepository propostaRepo;

	@Autowired
	CartaoRepository cartaoRepo;

	@Autowired
	VinculaCartaoClient vinculaCartao;
	
	private final Logger logger = LoggerFactory.getLogger(AssociaCartaoSchedule.class);

	@Scheduled(fixedDelayString = "${periodicidade.executa-operacao}")
	@Transactional
	public void VinculoCartaoProposta() {
		
		List<Proposta> propostas = propostaRepo.findByStatusAndCartaoId(Status.ELEGIVEL, null);

		try {

			for (Proposta proposta : propostas) {

				CartaoResponse cartao = vinculaCartao.pegaCartao(proposta.getId().toString());
					
				proposta.setCartao(cartao.toModel(proposta));
				
				propostaRepo.save(proposta);
				
				logger
				.info("Proposta de documento {} e cartao {} criados com sucesso!", 
						proposta.getCpfCnpj(), cartao.getId());

			}

		} catch (FeignException e) {

			e.printStackTrace();
		}

	
	}
}
