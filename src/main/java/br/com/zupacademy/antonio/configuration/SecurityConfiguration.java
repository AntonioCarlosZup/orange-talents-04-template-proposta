package br.com.zupacademy.antonio.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.authorizeRequests(authorizeRequests ->
	                authorizeRequests
	                		.antMatchers(HttpMethod.POST, "/proposta/**").hasAuthority("SCOPE_app-escopo")
	                        .antMatchers(HttpMethod.GET, "/proposta/propostas/{id}**").hasAuthority("SCOPE_app-escopo")
	                        .antMatchers(HttpMethod.POST, "/biometria/cartao/**").hasAuthority("SCOPE_app-escopo")  
	                        .anyRequest().authenticated()
	        )
	                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
	                
	  }

}
