package pl.awkward.security.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import pl.awkward.security.jwt.JwtManageComponent;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

@RequiredArgsConstructor
public class JwtVerifyUserFilter extends OncePerRequestFilter {

    private final JwtManageComponent jwtManageComponent;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String authorization = request.getHeader("Authorization");
        Jws<Claims> claimsJws;


        if (!this.jwtManageComponent.checkTokenType(authorization)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = Objects.requireNonNull(authorization).substring(7);

        if (this.jwtManageComponent.isParseCurrentKey(token)) {
            claimsJws = this.jwtManageComponent.parseClaimsCurrentKey(token);
            LocalDate refreshDate = this.jwtManageComponent.getRefreshDate(claimsJws);
            if (refreshDate.isBefore(LocalDate.now()))
                token = this.jwtManageComponent.createJwt(
                        this.jwtManageComponent.getId(claimsJws),
                        this.jwtManageComponent.getSubject(claimsJws),
                        this.jwtManageComponent.getAuthorities(claimsJws)
                );
        } else if (this.jwtManageComponent.isParseLastKey(token)) {
            claimsJws = this.jwtManageComponent.parseClaimsLastKey(token);
            token = this.jwtManageComponent.createJwt(
                    this.jwtManageComponent.getId(claimsJws),
                    this.jwtManageComponent.getSubject(claimsJws),
                    this.jwtManageComponent.getAuthorities(claimsJws)
            );
        } else {
            claimsJws = this.jwtManageComponent.parseClaimsCurrentKey(token);
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                this.jwtManageComponent.getId(claimsJws),
                this.jwtManageComponent.getSubject(claimsJws),
                this.jwtManageComponent.getAuthorities(claimsJws)
        );

        this.jwtManageComponent.addAuthorizationHeaderToResponse(response, token);

        SecurityContextHolder.getContextHolderStrategy().clearContext();
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}
