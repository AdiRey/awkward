package pl.awkward.security.jwt;

import com.google.common.base.Strings;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import pl.awkward.configuration.profiles.ProdProfile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.KeyPair;

@ProdProfile
@Component
public class JwtCreateFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
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
        return new UsernamePasswordAuthenticationToken(null, null, null);
    }
}
