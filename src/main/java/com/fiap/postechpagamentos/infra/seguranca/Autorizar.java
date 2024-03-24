package com.fiap.postechpagamentos.infra.seguranca;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Autorizar {

	public void usuario(Usuario usuario) {

		List<GrantedAuthority> regrasDeAutorizacao = new ArrayList<>();

		UserDetails criarUsuario = new User(usuario.nome(), "", regrasDeAutorizacao);

		var authentication = new UsernamePasswordAuthenticationToken(usuario, null, criarUsuario.getAuthorities());

		SecurityContextHolder
			.getContext()
			.setAuthentication(authentication);
	}
}

