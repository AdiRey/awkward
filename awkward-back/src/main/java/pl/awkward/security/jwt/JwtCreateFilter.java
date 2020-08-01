package pl.awkward.security.jwt;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.awkward.configuration.profiles.DevProfile;
import pl.awkward.exceptions.WrongJsonFormatException;
import pl.awkward.jwtKey.KeyPairContainer;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.stream.Collectors;

@DevProfile
@RequiredArgsConstructor
public class JwtCreateFilter extends UsernamePasswordAuthenticationFilter {

    private final KeyPairContainer keyPairContainer;

    @Override
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            String collect = request.getReader().lines().collect(Collectors.joining());

            JSONObject jsonObject = new JSONObject(collect);
            String login = jsonObject.getString("login");
            String password = jsonObject.getString("password");
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    login, password
            );
            return super.getAuthenticationManager().authenticate(authentication);

        } catch (IOException | JSONException ex) {
            ex.printStackTrace();
            //TODO: add thrown a exception
            throw new WrongJsonFormatException();
        }
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult)
            throws IOException, ServletException {

        String token = Jwts
                .builder()
                .setSubject(authResult.getName())
                .setIssuer("AdiRey")
                .setAudience("MeetMe users")
                .setIssuedAt(Date.valueOf(LocalDate.now()))
                .setExpiration(Date.valueOf(LocalDate.now().plusDays(10)))
                .signWith(this.keyPairContainer.getCurrentKeyPair().getPrivate())
                .compact();
        response.addHeader("Authorization", "Bearer " + token);
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed)
            throws IOException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed);
    }
}
