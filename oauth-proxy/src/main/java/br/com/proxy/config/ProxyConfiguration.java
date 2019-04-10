package br.com.proxy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class ProxyConfiguration extends ResourceServerConfigurerAdapter {

	private static final String RESOURCE_ID = "USER_CLIENT_RESOURCE";

	@Override
	public void configure(final HttpSecurity http) throws Exception {
		// @formatter:off
        http.authorizeRequests().
            antMatchers("/oauth/**").
            permitAll().
            antMatchers("/**").
            authenticated();
		// @formatter:on
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId(RESOURCE_ID);
	}
}