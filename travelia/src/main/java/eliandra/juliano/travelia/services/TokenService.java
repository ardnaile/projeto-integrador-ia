package eliandra.juliano.travelia.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import eliandra.juliano.travelia.models.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class TokenService {
    // chave secreta usada para gerar o token
    @Value("${api.security.token.secret}")
    private String secret;

    // identificador de quem está gerando a chave
    @Value("${api.security.token.issuer}")
    private String issuer;

    // por quanto tempo será válido o token
    @Value("${api.security.token.expiration-minutes}")
    private long expirationMinutes;
    public String generateToken(String usuario) {
        Algorithm algorithm = Algorithm.HMAC512(this.secret);
        Instant issuedAt = Instant.now().truncatedTo(ChronoUnit.SECONDS);
        Instant expiration = issuedAt.plus(expirationMinutes, ChronoUnit.MINUTES);
        try {
            String token = JWT.create()
                    .withIssuer(this.issuer)
                    .withSubject(Usuario.getUsername())
                    .withIssuedAt(issuedAt)
                    .withExpiresAt(expiration)
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating the JWT token");
        }
    }
    public String validateToken(String token) {}
}
