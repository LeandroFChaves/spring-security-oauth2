package br.com.resource.server.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

	@GetMapping("/admins-1")
	@PreAuthorize("hasAuthority('role_admin')")
	public String contextAdmin() {
		return "admin";
	}

	@GetMapping("/users-1")
	@PreAuthorize("hasAnyAuthority('role_admin','role_user')")
	public String contextUser() {
		return "user";
	}

	@GetMapping("/common-1")
	public String general() {
		return "common api success";
	}

}