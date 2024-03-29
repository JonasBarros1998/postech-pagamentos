package com.fiap.postechpagamentos.infra.seguranca;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class Seguranca {

	TokenRequestFiltro tokenRequestFiltro;

	Seguranca(TokenRequestFiltro tokenRequestFiltro) {
		this.tokenRequestFiltro = tokenRequestFiltro;
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(csrf -> csrf.disable())
			.sessionManagement(session ->
				session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authorizeHttpRequests(requisicao -> requisicao.anyRequest().authenticated())
			.addFilterBefore(tokenRequestFiltro, UsernamePasswordAuthenticationFilter.class)
			.build();
	}
}
