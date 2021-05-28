package br.com.zupacademy.antonio.feigns;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.zupacademy.antonio.dtos.CartaoResponse;

@FeignClient(name="vincula-cartao", url="${client.cartao}")
public interface VinculaCartaoClient {

	//esta pegando o get de cartão que não puxa o id do cartão, portanto tem que ser o get normal
	//sem o PathVariable como id
	@GetMapping("/api/cartoes/")
	CartaoResponse pegaCartao(@RequestParam("idProposta") String idProposta);
}
