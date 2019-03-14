package br.com.authorization.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.authorization.server.dao.UserDAO;
import br.com.authorization.server.entity.User;

@RestController
@RequestMapping("/oauth/api")
public class UserController {

	@Autowired
	private UserDAO userDao;
	
	@GetMapping("/public/")
	public Page<User> user(Pageable pageable, @RequestParam String username) {
		return userDao.findByUserName(username, pageable);
	}

	@GetMapping("/public/all")
	public Page<User> userall(Pageable pageable) {
		return userDao.findAll(pageable);
	}

}