package br.com.resource.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	private static final String RESOURCE_ID = "USER_CLIENT_RESOURCE";
	
	@Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
	private String urlCheckToken;
	
	@Value("${spring.security.oauth2.resourceserver.client-id}")
	private String clientId;
	
	@Value("${spring.security.oauth2.resourceserver.client-secret}")
	private String clientSecret;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http
			.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/usuario-logado-1").hasRole("ADMIN")
				.antMatchers("/admins-1").hasRole("ADMIN")
			.and()
			.authorizeRequests()
				.antMatchers(HttpMethod.GET,"/**").access("#oauth2.hasScope('read')")
				.antMatchers(HttpMethod.POST,"/**").access("#oauth2.hasScope('write')")
				.antMatchers(HttpMethod.PUT,"/**").access("#oauth2.hasScope('write')")
				.antMatchers(HttpMethod.PATCH,"/**").access("#oauth2.hasScope('write')")
				.antMatchers(HttpMethod.DELETE,"/**").access("#oauth2.hasScope('delete')")
		 	.anyRequest()
		 	.authenticated()
		 	.and()
		 	.sessionManagement()
		 	.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		 	.csrf().disable();
		// @formatter:on
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId(RESOURCE_ID).tokenServices(tokenServices()).stateless(true);
	}

    @Primary
    @Bean
    public RemoteTokenServices tokenServices() {
        final RemoteTokenServices tokenService = new RemoteTokenServices();
        
        tokenService.setCheckTokenEndpointUrl(urlCheckToken);
        tokenService.setClientId(clientId);
        tokenService.setClientSecret(clientSecret);
        
        return tokenService;
    }
}