package pl.awkward.jwtKey;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.time.LocalDate;

@Service
@EnableScheduling
@RequiredArgsConstructor
@Getter
public class KeyPairContainer {

    private final JwtKeysRepository jwtKeysRepository;

    private static final SignatureAlgorithm ALG = SignatureAlgorithm.RS256;

    private KeyPair currentKeyPair = Keys.keyPairFor(ALG);
    private KeyPair lastKeyPair;

    private JwtKeys jwtKeys;


    @Scheduled(cron = "0 0 0 * * 1")
    public void refreshKeys() {
        this.lastKeyPair = this.currentKeyPair;
        this.currentKeyPair = Keys.keyPairFor(ALG);
        setFields();
        this.jwtKeysRepository.save(jwtKeys);
    }

    private void setFields() {
        this.jwtKeys = new JwtKeys();
        this.jwtKeys.setPrivateKey(Encoders.BASE64.encode(this.currentKeyPair.getPrivate().getEncoded()));
        this.jwtKeys.setPublicKey(Encoders.BASE64.encode(this.currentKeyPair.getPublic().getEncoded()));
        jwtKeys.setEffectiveDate(LocalDate.now());
    }
}
