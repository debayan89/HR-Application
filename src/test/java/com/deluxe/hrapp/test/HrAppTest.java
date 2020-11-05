/**
 * 
 */
package com.deluxe.hrapp.test;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.deluxe.hrapp.jwtdecoder.JwtDecoder;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class HrAppTest {

	@Autowired
	private JwtDecoder jwtDecoder;
	String jwtTokenPass;
	String jwtTokenFail;
	private static final String SECRET_KEY = "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=";
	@Before
	public void setup() {
		jwtTokenPass =  createJWT("TestUser", "hrapplogin" , "deluxeCorp");
		jwtTokenFail =  createJWT("TestUser", "hrapploginnnnn" , "deluxeCorppppp");
		
	}
	

	
	@Test
	public void jwtDecodertestPass() {
		
		Assert.assertTrue(jwtDecoder.validateToken(jwtTokenPass));		
		
		
	}
	
	
	@Test
	public void jwtDecodertestFail() {
			
		Assert.assertFalse(jwtDecoder.validateToken(jwtTokenFail));			
		
	}
	
	public String createJWT(String username, String subject, String issuer) {
		  
	    //The JWT signature algorithm we will be using to sign the token
	    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

	    long nowMillis = System.currentTimeMillis();
	    Date now = new Date(nowMillis);
	    Date targetTime = new Date(); //now
	    targetTime = DateUtils.addMinutes(targetTime, 10); //add minute

	    //We will sign our JWT with our ApiKey secret
	    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
	    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

	    //Let's set the JWT Claims
	    JwtBuilder builder = Jwts.builder().setId(username)
	            .setIssuedAt(now)
	            .setSubject(subject)
	            .setIssuer(issuer)
	            .setExpiration(targetTime)
	            .signWith(signatureAlgorithm, signingKey); 
	  
	    //Builds the JWT and serializes it to a compact, URL-safe string
	    return builder.compact();
	}


}
