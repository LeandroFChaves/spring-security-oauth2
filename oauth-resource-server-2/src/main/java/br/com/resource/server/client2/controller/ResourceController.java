package br.com.resource.server.client2.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

	@GetMapping("/admins-2")
	@PreAuthorize("hasAuthority('role_admin')")
	public String contextAdmin() {
		return "admin";
	}

	@GetMapping("/users-2")
	@PreAuthorize("hasAnyAuthority('role_admin','role_user')")
	public String contextUser() {
		return "user";
	}

	@GetMapping("/common-2")
	public String general() {
		return "common api success";
	}

}