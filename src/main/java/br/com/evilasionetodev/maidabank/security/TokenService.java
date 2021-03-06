package br.com.evilasionetodev.maidabank.security;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.evilasionetodev.maidabank.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	private String secret ="+&lgRn4f8!Pkr*LVI7]h]Rvfa(2M*HRXgZJgNu$7XD$v\\SQy+Fc7cHPF>GTmR.2/Cs[y$L&3&dr(U@(z-}FiFd#%!h9OdynzM+J+HY#t/>BkDg{#qr1mP)K.jme/LCO[vET77lq!O-*)g<(eUF1aaD";
	
	public String generateToken(Authentication authentication) {
		User loged = (User) authentication.getPrincipal();
		Date now = new Date();
		return Jwts.builder()
				.setIssuer("API test")
				.setSubject(loged.getId().toString())
				.setIssuedAt(now)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

	public boolean isTokenValid(String token) {
		try {		
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long getIdUser(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}
	
	public String retrieveToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		return token.substring(7, token.length());
	}

}