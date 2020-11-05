package com.deluxe.hrapp.jwtdecoder;

import java.util.Date;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class JwtDecoder 
{
	
	private static final String SECRET_KEY = "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=";
	private static final String ISSUER = "deluxeCorp";
	private static final String SUBJECT = "hrapplogin";
	
	
	public static Claims decodeJWT(String jwt) {
	    //This line will throw an exception if it is not a signed JWS (as expected)
		try {
	    Claims claims = Jwts.parser()
	            .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
	            .parseClaimsJws(jwt).getBody();
	    return claims;
		} catch (Exception err) {
			return null;
		}
	}
	
	
	public static boolean validateToken(String jwt) {
		if(StringUtils.isEmpty(jwt)) {
			return false;
		}
		Claims claims = decodeJWT(jwt);
		if(null == claims) {
			return false;
		}
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		if(ISSUER.equalsIgnoreCase(claims.getIssuer()) && SUBJECT.equalsIgnoreCase(claims.getSubject())
				&& (now.compareTo(claims.getExpiration())< 0)) {
			return true;
		}
		return false;
	}
	
}