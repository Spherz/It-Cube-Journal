package com.itcube.journal.config.security.converter;

import com.itcube.journal.dto.auth.UserInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserInfoJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private final CustomJwtAuthenticationConverter grantedAuthoritiesConverter;

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        Collection<GrantedAuthority> authorities = grantedAuthoritiesConverter.convert(jwt);

        UserInfoDTO principal = new UserInfoDTO(
                jwt.getSubject(),
                jwt.getClaimAsString("preferred_username"),
                jwt.getClaimAsString("email"),
                Optional.ofNullable(jwt.getClaimAsStringList("roles")).orElse(List.of()),
                true, // reaching this converter means the JwtDecoder already validated signature/expiry
                jwt.getClaimAsString("given_name"),
                jwt.getClaimAsString("family_name")
        );

        return new UsernamePasswordAuthenticationToken(principal, jwt, authorities);
    }
}
