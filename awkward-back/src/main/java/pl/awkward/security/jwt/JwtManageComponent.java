package pl.awkward.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import pl.awkward.jwtKey.KeyPairContainer;

import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtManageComponent {

    private final JwtConfig jwtConfig;
    private final KeyPairContainer keyPairContainer;

    public String createJwt(final Long id, final String subject, final Collection<? extends GrantedAuthority> authorities) {
        return Jwts
                .builder()
                .claim(this.jwtConfig.getClientIdHeaderName(), id)
                .setSubject(subject)
                .setIssuer(this.jwtConfig.getIssuer())
                .setAudience(this.jwtConfig.getAudience())
                .setIssuedAt(Date.valueOf(LocalDate.now()))
                .claim(this.jwtConfig.getRefreshName(), Date.valueOf(LocalDate.now().plusDays(this.jwtConfig.getRefreshAfterDays())))
                .setExpiration(Date.valueOf(LocalDate.now().plusDays(this.jwtConfig.getTokenLife())))
                .claim(this.jwtConfig.getAuthorityHeader(), authorities)
                .signWith(this.keyPairContainer.getCurrentKeyPair().getPrivate())
                .compact();
    }

    public boolean isParseCurrentKey(final String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(this.keyPairContainer.getCurrentKeyPair().getPublic())
                .build()
                .isSigned(token);
    }

    public Jws<Claims> parseClaimsCurrentKey(final String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(this.keyPairContainer.getCurrentKeyPair().getPublic())
                .build()
                .parseClaimsJws(token);
    }

    public boolean isParseLastKey(final String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(this.keyPairContainer.getLastKeyPair().getPublic())
                .build()
                .isSigned(token);
    }

    public Jws<Claims> parseClaimsLastKey(final String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(this.keyPairContainer.getLastKeyPair().getPublic())
                .build()
                .parseClaimsJws(token);
    }

    public void addAuthorizationHeaderToResponse(final HttpServletResponse response, final String token) {
        response.addHeader(this.jwtConfig.getHeaderName(), this.jwtConfig.getPrefixTokenType() + token);
    }

    public LocalDate getRefreshDate(final Jws<Claims> claimsJws) {
        return LocalDate.ofInstant(Instant.ofEpochMilli((Long)claimsJws.getBody().get(this.jwtConfig.getRefreshName())), ZoneId.systemDefault());
    }

    public boolean checkTokenType(final String authorization) {
        return (authorization != null ? authorization : "").startsWith(this.jwtConfig.getPrefixTokenType());
    }

    public Long getId(final Jws<Claims> claimsJws) {
        return Long.parseLong(String.valueOf(claimsJws.getBody().get(this.jwtConfig.getClientIdHeaderName())));
    }

    public String getSubject(final Jws<Claims> claimsJws) {
        return claimsJws.getBody().getSubject();
    }

    public Collection<? extends GrantedAuthority> getAuthorities(final Jws<Claims> claimsJws) {
        List<Map<String, String>> authorities = (List<Map<String, String>>) claimsJws.getBody().get(this.jwtConfig.getAuthorityHeader());
        return authorities.stream().map(
                simple -> new SimpleGrantedAuthority(simple.get("authority"))
        ).collect(Collectors.toCollection(ArrayList::new));
    }

}
