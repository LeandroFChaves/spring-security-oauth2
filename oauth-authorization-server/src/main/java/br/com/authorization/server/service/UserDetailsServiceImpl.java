package br.com.authorization.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.authorization.server.dao.UserDAO;
import br.com.authorization.server.entity.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserDAO userDao;

	@Override
	public UserDetails loadUserByUsername(String username) {
		User usuario = userDao.findByUserName(username);

		if (usuario == null) {
			throw new UsernameNotFoundException("Usuário e/ou senha incorretos - " + username);
		}

		return usuario;
	}

}
