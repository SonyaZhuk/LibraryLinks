package org.library.security;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import java.text.ParseException;
import java.util.AbstractMap;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;
import org.library.exception.JwtVerificationException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;

public class CustomJwtDecoder implements JwtDecoder {
  private final Map<String, String> jwtPublicKeys;

  public CustomJwtDecoder( Map<String, String> jwtPublicKeys) {
    this.jwtPublicKeys = jwtPublicKeys;
  }

  @Override
  public Jwt decode(String token) {
    try {
      final SignedJWT signedJWT = SignedJWT.parse(token);
      final JWTClaimsSet jwtClaimsSet = signedJWT.getJWTClaimsSet();
      RSAKey publicKey = preparePublicKey(jwtClaimsSet);
      RSASSAVerifier verifier = new RSASSAVerifier(publicKey);
      verifyJwt(verifier, signedJWT);
      final JWSHeader jwsHeader = signedJWT.getHeader();
      return Jwt.withTokenValue(token)
          .headers(headers -> headers.putAll(convertHeaders(jwsHeader)))
          .claims(claims -> claims.putAll(convertClaims(jwtClaimsSet.getClaims())))
          .build();
    } catch (JOSEException | ParseException e) {
      throw new JwtVerificationException(e.getMessage());
    }
  }

  private RSAKey preparePublicKey(JWTClaimsSet jwtClaimsSet)
      throws ParseException, JOSEException {
    String publicKeyName = jwtClaimsSet.getStringClaim("iss");
    if (jwtPublicKeys.containsKey(publicKeyName)) {
      String publicKey = jwtPublicKeys.get(publicKeyName);
      JWK jwk = JWK.parseFromPEMEncodedObjects(publicKey);
      return jwk.toRSAKey();
    } else {
      throw new JwtVerificationException(
          String.format("Unknown public key name: %s", publicKeyName));
    }
  }

  private Map<String, Object> convertHeaders(JWSHeader jwsHeader) {
    return Map.of("alg", jwsHeader.getAlgorithm().toString());
  }

  private Map<String, Object> convertClaims(Map<String, Object> claims) {
    Map<String, Object> converted = claims.entrySet().stream().map(e -> {
      AbstractMap.SimpleEntry<String, Object> entry;
      if (e.getValue() instanceof Date) {
        entry = new AbstractMap.SimpleEntry<>(e.getKey(), ((Date) e.getValue()).toInstant());
      } else {
        entry = new AbstractMap.SimpleEntry<>(e.getKey(), e.getValue());
      }
      return entry;
    }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    return converted;
  }

  private void verifyJwt(JWSVerifier verifier, SignedJWT signedJWT) throws JOSEException {
    if (!signedJWT.verify(verifier)) {
      throw new JwtVerificationException("Error while verify jws");
    }
  }
}
