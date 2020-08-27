package pl.awkward.security.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "adi-rey.jwt")
@Setter
@Getter
public class JwtConfig {

    private String issuer;
    private String audience;
    private HttpHeader headerName;
    private TokenType tokenType;
    private Integer tokenLife = 6;
    private String authorityHeader = "authorities";
    private String clientIdHeaderName = "client_id";
    private Integer refreshAfterDays = 3;
    private String refreshName = "rad";

    public String getPrefixTokenType() {
        return this.tokenType.tokenPrefix;
    }

    public String getHeaderName() {
        return this.headerName.header;
    }


    @Getter
    private enum TokenType {

        JWT("Bearer ");

        TokenType(String tokenPrefix) {
            this.tokenPrefix = tokenPrefix;
        }

        private final String tokenPrefix;
    }

    @Getter
    private enum HttpHeader {
        AUTHORIZATION("Authorization");
        HttpHeader(String header) {
            this.header = header;
        }

        private final String header;
    }
}
