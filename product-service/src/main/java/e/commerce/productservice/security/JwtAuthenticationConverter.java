package e.commerce.productservice.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Component
public class JwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    @Override
    public AbstractAuthenticationToken convert(@NonNull Jwt jwt) {
        String email = jwt.getClaim(JwtClaims.EMAIL_CLAIM);
        String firstname = jwt.getClaim(JwtClaims.FIRSTNAME_CLAIM);
        String lastname = jwt.getClaim(JwtClaims.LASTNAME_CLAIM);
        String phone = jwt.getClaim(JwtClaims.PHONE_CLAIM);
        String birthdate = jwt.getClaim(JwtClaims.BIRTHDATE_CLAIM);
        List<SimpleGrantedAuthority> roles = extractRoles(jwt);

        SecurityUserPrincipal principal = SecurityUserPrincipal.builder()
                .email(email)
                .firstname(firstname)
                .lastname(lastname)
                .phone(phone)
                .birthdate(LocalDate.parse(birthdate))
                .roles(roles)
                .build();

        return new UsernamePasswordAuthenticationToken(principal, "", roles);
    }

    @SuppressWarnings("unchecked")
    private List<SimpleGrantedAuthority> extractRoles(Jwt jwt) {
        Map<String, Object> realmClaim = (Map<String, Object>) jwt.getClaims().get(JwtClaims.REALM_CLAIM);
        List<String> roles = (List<String>) realmClaim.get(JwtClaims.ROLES_CLAIM);
        return roles.stream()
                .map(role -> String.format("%s%s", JwtClaims.ROLE_PREFIX, role))
                .map(SimpleGrantedAuthority::new)
                .toList();
    }
}
