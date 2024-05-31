package it.epicode.Events.config;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import it.epicode.Events.businesslayer.services.security.SecurityUserDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {

	@Value("${jwt.key}")
	private String securityKey;
	@Value("${jwt.expirationMs}")
	private long expirationMs;

	public String generateToken(Authentication auth) {
		byte[] keyBytes = securityKey.getBytes();
		Key key = Keys.hmacShaKeyFor(keyBytes);

		var user = (SecurityUserDetails) auth.getPrincipal();
		var token = Jwts.builder()
				.subject(user.getUsername())
				.issuedAt(new Date())
				.issuer("MySpringApplication")
				.expiration(new Date(new Date().getTime() + expirationMs))
				.signWith(key)
				.compact();
		return token;
	}

	public boolean isTokenValid(String token) {
		try {
			byte[] keyBytes = securityKey.getBytes();
			SecretKey key = Keys.hmacShaKeyFor(keyBytes);
			Jwts.parser()
					.verifyWith(key).requireIssuer("MySpringApplication");
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String getUsernameFromToken(String token) {
		byte[] keyBytes = securityKey.getBytes();
		SecretKey key = Keys.hmacShaKeyFor(keyBytes);
		var username = Jwts.parser()
				.verifyWith(key).build()
				.parseSignedClaims(token).getPayload().getSubject();
		return username;
	}
}
