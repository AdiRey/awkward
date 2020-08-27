package pl.awkward.security.jwtKey;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JwtKeysRepository extends JpaRepository<JwtKeys, Long> {
}
