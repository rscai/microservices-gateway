package io.github.rscai.microservices.gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {
  @Bean
  public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http,
      ReactiveClientRegistrationRepository clientRegistrationRepository) {
    // Authenticate through configured OpenID Provider
    http.oauth2Login();

    // Require authentication for all requests
    http.authorizeExchange().anyExchange().authenticated();

    // Disable CSRF in the gateway to prevent conflicts with proxied service CSRF
    http.csrf().disable();
    return http.build();
  }
}
