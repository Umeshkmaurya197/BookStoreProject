package com.git.bookstore.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import org.springframework.stereotype.Component;

@Component
public class TokenUtility {
    private static final String TOKEN_SECRET = "Signature";

    //use to create new token
    public static String createToken(int userId) {
        try {
            //to set Algorithm
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);

            return JWT.create().withClaim("user_id", userId).sign(algorithm);
        } catch (JWTCreationException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return "out of try block : encode ";
    }

    //use to decode the token
    public int decodeToken(String token) {
        int userId;
        //for verification algorithm
        Verification verification = null;
        try {
            verification = JWT.require(Algorithm.HMAC256(TOKEN_SECRET));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        assert verification != null;
        JWTVerifier jwtVerifier = verification.build();

        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        Claim claim = decodedJWT.getClaim("user_id");
        userId = claim.asInt();
        return userId;
    }

}
