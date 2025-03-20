package com.employee.demo.helper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
 
import org.springframework.stereotype.Component;
 
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import io.jsonwebtoken.security.Keys;
 
@Component
public class JwtTokenHelper {
	private static final long EXPIRATION_TIME = 8 * 60 * 1000;
    private static final String SECRET_KEY = Base64.getEncoder().encodeToString("this_will_be_Very_Long_Secret_Key_That_Is_Secure_And_Strong".getBytes());


    private Key getSigningKey() {
    	return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }
 
// generating jwt token by getting emailId.
    public String generateToken(String emailId) {
        return Jwts.builder()
                .setSubject(emailId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }
 
 // getting emailId from token.
    public String getEmailIdFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build()
                .parseClaimsJws(token).getBody().getSubject();
    }
 
// checking the token is valid or not
    public boolean validateToken(String token, String email) {
        return email.equals(getEmailIdFromToken(token)) && !isTokenExpired(token);
    }
    
// checking is token get expired.
    private boolean isTokenExpired(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build()
                .parseClaimsJws(token).getBody().getExpiration().before(new Date());
    }
}