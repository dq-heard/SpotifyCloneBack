package app.dq_heard.clone_backend.infrastructure.config;

import app.dq_heard.clone_backend.infrastructure.CustomJWTAuthenticationConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

@Configuration
public class SecurityConfiguration {

  @Bean
  public SecurityFilterChain configure(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(authorize -> authorize
        .requestMatchers(HttpMethod.GET, "/api/songs").permitAll()
        .requestMatchers(HttpMethod.GET, "/api/songs/search").permitAll()
        .anyRequest().authenticated())
        .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
            .csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler()))
        .oauth2Login(Customizer.withDefaults())
        .oauth2ResourceServer(oauth2 -> oauth2
            .jwt(jwt -> jwt
                .jwtAuthenticationConverter(new CustomJWTAuthenticationConverter())
                .decoder(jwtDecoder())))
        .oauth2Client(Customizer.withDefaults());

    return http.build();
  }

  @Bean
  public JwtDecoder jwtDecoder() {
    // Configure the JwtDecoder with the JWK set URI from Auth0
    return NimbusJwtDecoder.withJwkSetUri("https://dev-m3p6dptt4vt1z8gl.us.auth0.com/.well-known/jwks.json").build();
  }
}
