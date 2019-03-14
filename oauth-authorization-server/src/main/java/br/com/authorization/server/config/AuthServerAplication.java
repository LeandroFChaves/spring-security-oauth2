package br.com.authorization.server.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"br.com.authorization.server"})
@EntityScan({"br.com.authorization.server.entity"})
@EnableJpaRepositories("br.com.authorization.server.dao")
public class AuthServerAplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthServerAplication.class, args);
	}

}
