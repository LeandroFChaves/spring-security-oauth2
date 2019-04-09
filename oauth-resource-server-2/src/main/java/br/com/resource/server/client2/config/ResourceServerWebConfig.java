package br.com.resource.server.client2.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan({ "br.com.resource.server.client2.controller" })
public class ResourceServerWebConfig implements WebMvcConfigurer {

}