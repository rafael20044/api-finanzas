package dev.rafaelbarragan.api.finanza.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.JWTVerifier;
import dev.rafaelbarragan.api.finanza.domain.user.entity.User;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    private final String secret = System.getenv("SECRET");

    public String generarToken(User user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("finanza")
                    .withSubject(user.getUsername())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new JWTCreationException(exception.getMessage(), exception.getCause());
        }
    }

    public String getSubject(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    // specify any specific claim validations
                    .withIssuer("finanza")
                    // reusable verifier instance
                    .build();

            String subject = verifier.verify(token).getSubject();
            if (subject != null) {
                return subject;
            }
            throw new JWTVerificationException("Token no valido");
        } catch (JWTVerificationException exception){
            throw new JWTVerificationException(exception.getMessage());
        }
    }
}
