package br.com.authorization.server.controller;

import java.security.Principal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("authorizationRequest")
public class OAuth2Controller {

	@Autowired
	private ClientDetailsService clientDetailsService;
	
	//@RequestMapping("/oauth/authorize")
	public ModelAndView getAccessConfirmation(Map<String, Object> model, Principal principal) throws Exception {
        AuthorizationRequest clientAuth = (AuthorizationRequest) model.remove("authorizationRequest");
        ClientDetails client = clientDetailsService.loadClientByClientId(clientAuth.getClientId());
        model.put("auth_request", "clientAuth");
        model.put("client", client);
        model.put("status", "status");
        
        return new ModelAndView("authorize", model);
    }

}
