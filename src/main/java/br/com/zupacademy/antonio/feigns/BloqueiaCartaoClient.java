package br.com.zupacademy.antonio.feigns;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.zupacademy.antonio.dtos.BloqueioRequest;
import br.com.zupacademy.antonio.dtos.BloqueioResponse;

@FeignClient(name = "bloqueia-cartao", url="${client.cartao}")
public interface BloqueiaCartaoClient {
	
	@PostMapping("/api/cartoes/{id}/bloqueios")
	public BloqueioResponse bloqueioCartao(@RequestParam("id") String idCartao, @RequestBody BloqueioRequest request);

}
