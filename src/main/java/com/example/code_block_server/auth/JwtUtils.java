package com.example.code_block_server.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

public class JwtUtils {

    public static String generateJwtString(String userSubject) {
        return JWT.create()
                .withIssuer("cdBlock")
                .withSubject(userSubject)
                .sign(Algorithm.HMAC512("secret"));
    }

    public void verifyJwtString(String incomingJWT, String userSubject) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .withSubject(userSubject)
                    .build();
            verifier.verify(incomingJWT);
        } catch (JWTVerificationException exception){
            throw new JWTVerificationException("Your access had been denied@");
        }
    }

    public static DecodedJWT decodeJwt(String token) {
        try {
            return JWT.decode(token);
        } catch (JWTDecodeException e) {
            throw new JWTDecodeException("You could not decode this token");
        }
    }
}
