package com.itcube.journal.config.security.converter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomJwtAuthenticationConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {

        Set<GrantedAuthority> authorities = new HashSet<>();

        List<String> customRoles = jwt.getClaimAsStringList("roles");

        if (customRoles != null) {
            customRoles.forEach(role ->
                    authorities.add(new SimpleGrantedAuthority(role)));
        }

        log.info("Extracted authorities from jwt: {}", authorities);

        return authorities;
    }
}
