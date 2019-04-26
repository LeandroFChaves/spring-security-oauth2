package br.com.authorization.server.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeradorSenha {

	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		System.out.println("Senha usuário admin: " + encoder.encode("admin"));
		System.out.println("Senha usuário user: " + encoder.encode("user"));
		
		System.out.println("Senha client_app_1: " + encoder.encode("client_app_1"));
		System.out.println("Senha client_app_2: " + encoder.encode("client_app_2"));
		
	}

}