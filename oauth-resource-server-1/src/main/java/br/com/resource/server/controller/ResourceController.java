package br.com.resource.server.controller;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

	@GetMapping("/usuario-logado-1")
    public ResponseEntity<Principal> get(final Principal principal) {
        return ResponseEntity.ok(principal);
    }
	
	@GetMapping("/testes-1")
	public String testes() {
		return "Método sem restrição de acesso";
	}
	
	@GetMapping("/admins-1")
	@PreAuthorize("hasAnyAuthority('pesquisar')")
	public String contextAdmin() {
		return "admin";
	}

	@GetMapping("/users-1")
	@PreAuthorize("hasAnyAuthority('pesquisar')")
	public String contextUser() {
		return "user";
	}

	@PutMapping("/admins-1")
	@PreAuthorize("hasAnyAuthority('alterar')")
	public String updateAdmin() {
		return "Admin atualizado com sucesso!";
	}
	
	@PutMapping("/users-1")
	@PreAuthorize("hasAnyAuthority('alterar')")
	public String updateUser() {
		return "Usuário atualizado com sucesso!";
	}
	
	@DeleteMapping("/admins-1")
	@PreAuthorize("hasAnyAuthority('excluir')")
	public String deleteAdmin() {
		return "Admin deletado com sucesso!";
	}
	
	@DeleteMapping("/users-1")
	@PreAuthorize("hasAnyAuthority('excluir')")
	public String deleteUser() {
		return "Usuário deletado com sucesso!";
	}	
	
}