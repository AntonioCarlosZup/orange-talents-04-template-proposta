package br.com.zupacademy.antonio.feigns;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import br.com.zupacademy.antonio.dtos.CartaoResponse;

@FeignClient(name="vincula-cartao", url="${client.cartao}")
public interface VinculaCartaoClient {

	@GetMapping("/api/cartoes/{id}")
	CartaoResponse pegaCartao(@PathVariable String idProposta);
}
