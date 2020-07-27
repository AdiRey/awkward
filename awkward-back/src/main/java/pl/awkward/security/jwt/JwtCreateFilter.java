package pl.awkward.security.jwt;

import com.google.common.base.Strings;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.KeyPair;

@Component
public class JwtCreateFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        if (Strings.isNullOrEmpty(authorization) || !authorization.startsWith("Bearer ")) {
            KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.RS256);
            String jws = Jwts
                    .builder()
                    .setSubject("XD")
                    .signWith(keyPair.getPrivate())
                    .compact();
            response.addHeader("Authorization", "Bearer " + jws);
        }
        filterChain.doFilter(request, response);
    }
}
