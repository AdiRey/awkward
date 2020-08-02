package pl.awkward.security.jwt;

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

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@DevProfile
@RequiredArgsConstructor
public class JwtCreateFilter extends UsernamePasswordAuthenticationFilter {
    private final static String LOGIN = "login";
    private final static String PASSWORD = "password";

    private final JwtManageComponent jwtManageComponent;

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
            String login = jsonObject.getString(LOGIN);
            String password = jsonObject.getString(PASSWORD);
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

        String[] values = authResult.getName().split("-");
        String token = this.jwtManageComponent.createJwt(Long.parseLong(values[0]), values[1], authResult.getAuthorities());
        this.jwtManageComponent.addAuthorizationHeaderToResponse(response, token);
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed)
            throws IOException, ServletException {
        System.out.println("TEST");
        super.unsuccessfulAuthentication(request, response, failed);
    }
}
