package app.dq_heard.clone_backend.infrastructure;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.Collection;
import java.util.stream.Collectors;

public class CustomJWTAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private final JwtGrantedAuthoritiesConverter authoritiesConverter = new JwtGrantedAuthoritiesConverter();

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        Collection<SimpleGrantedAuthority> authorities = extractAuthorities(jwt);
        return new JwtAuthenticationToken(jwt, authorities, "sub"); // "sub" is used as the username
    }

    private Collection<SimpleGrantedAuthority> extractAuthorities(Jwt jwt) {
        Collection<GrantedAuthority> authorities = authoritiesConverter.convert(jwt);
        // Add custom logic to map authorities as needed
        // For example, you might want to add "ROLE_" prefix to each authority
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority("ROLE_" + authority.getAuthority()))
                .collect(Collectors.toList());
    }
}
