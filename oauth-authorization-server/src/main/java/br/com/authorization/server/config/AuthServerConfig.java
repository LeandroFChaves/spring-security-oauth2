package br.com.authorization.server.config;

import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import br.com.authorization.server.token.CustomTokenEnhancer;

@Configuration
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private DataSource dataSource;
	
    @Override
    public void configure(final AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.jdbc(dataSource);
		/*
        clients.inMemory()
        .withClient("sampleClientId")
        .authorizedGrantTypes("implicit")
        .scopes("read", "write", "foo", "bar")
        .autoApprove(false)
        .accessTokenValiditySeconds(3600)
        .redirectUris("http://localhost:8083/","http://localhost:8086/")
        .and()
        .withClient("fooClientIdPassword")
        .secret(passwordEncoder.encode("secret"))
        .authorizedGrantTypes("password", "authorization_code", "refresh_token", "client_credentials")
        .autoApprove(true)
        .scopes("foo", "read", "write")
        .accessTokenValiditySeconds(3600)       // 1 hour
        .refreshTokenValiditySeconds(2592000)  // 30 days
        .redirectUris("http://www.example.com","http://localhost:4200/", "http://localhost:8089/","http://localhost:8080/login/oauth2/code/custom","http://localhost:8080/ui-thymeleaf/login/oauth2/code/custom", "http://localhost:8080/authorize/oauth2/code/bael", "http://localhost:8080/login/oauth2/code/bael")
        .and()
        .withClient("barClientIdPassword")
        .secret(passwordEncoder.encode("secret"))
        .authorizedGrantTypes("password", "authorization_code", "refresh_token")
        .scopes("bar", "read", "write")
        .accessTokenValiditySeconds(3600)       // 1 hour
        .refreshTokenValiditySeconds(2592000)  // 30 days
        .and()
        .withClient("testImplicitClientId")
        .authorizedGrantTypes("implicit")
        .scopes("read", "write", "foo", "bar")
        .autoApprove(true)
        .redirectUris("http://www.example.com");*/
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), accessTokenConverter()));
		
		endpoints.tokenStore(tokenStore())
				 .tokenEnhancer(tokenEnhancerChain)
				 .reuseRefreshTokens(false)
				 .authenticationManager(authenticationManager)
				 .userDetailsService(userDetailsService);
	}

	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public TokenEnhancer tokenEnhancer() {
		return new CustomTokenEnhancer();
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
		accessTokenConverter.setSigningKey("teste");

		return accessTokenConverter;
	}

}
