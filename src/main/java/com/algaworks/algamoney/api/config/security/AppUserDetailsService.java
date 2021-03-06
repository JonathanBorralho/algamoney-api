package com.algaworks.algamoney.api.config.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.algaworks.algamoney.api.model.Usuario;
import com.algaworks.algamoney.api.repository.UsuarioRepo;

@Service
@Transactional
public class AppUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UsuarioRepo usuarioRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user = usuarioRepo.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou senha inválidos"));
		return new UsuarioSistema(user, getPermissoes(user));
	}

	private Collection<? extends GrantedAuthority> getPermissoes(Usuario user) {
		return user.getGrupos().stream()
						.map(g -> g.getPermissoes())
						.flatMap(List::stream)
						.map(p -> new SimpleGrantedAuthority("ROLE_" + p.getNome().toUpperCase()))
						.collect(Collectors.toSet());
	}

}
