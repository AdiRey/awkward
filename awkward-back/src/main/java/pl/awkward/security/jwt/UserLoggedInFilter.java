package pl.awkward.security.jwt;

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


public class UserLoggedInFilter extends OncePerRequestFilter {

    private static final String TOKEN_TYPE = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authorization = request.getHeader("Authorization");

        if (!(authorization != null ? authorization : "").startsWith(TOKEN_TYPE)) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = authorization.substring(7);

        Jwts
                .parserBuilder()
                .setSigningKey(Keys.keyPairFor(SignatureAlgorithm.RS256).getPublic())
                .build();
    }
}
