package org.backend.smallecommerceapi.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

/**
 * Service responsible for generating JWT access tokens.
 *
 * <p>The generated token contains information about the authenticated user,
 * including:</p>
 * <ul>
 *     <li>Token issuer</li>
 *     <li>Issued time</li>
 *     <li>Expiration time</li>
 *     <li>User identity</li>
 *     <li>User authorities/roles</li>
 * </ul>
 */
@Service
public class JwtService {

    /**
     * Identifies the application that issued the JWT.
     */
    @Value("${jwt.issuer}")
    private String issuer;

    /**
     * JWT lifetime in seconds.
     */
    @Value("${jwt.expiry}")
    private Long expiry;

    private final JwtEncoder jwtEncoder;

    /**
     * Creates a JWT service using the provided JWT encoder.
     *
     * @param jwtEncoder encoder used to sign and generate JWT tokens
     */
    public JwtService(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    /**
     * Generates a JWT access token for an authenticated user.
     *
     * <p>The user's username is stored in the {@code sub} claim,
     * while the user's authorities are stored in the
     * {@code authorities} claim.</p>
     *
     * @param authentication authenticated user information provided by Spring Security
     * @return signed JWT token as a string
     */
    public String generateToken(Authentication authentication) {

        // Current time is used as the token's issued time.
        Instant now = Instant.now();

        // Extract the user's authorities/roles from the authentication object.
        List<String> authorities =
                authentication.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList();

        /*
         * Build the JWT payload (claims).
         *
         * Example:
         *
         * {
         *     "iss": "small-ecommerce-api",
         *     "iat": 1727000000,
         *     "exp": 1727003600,
         *     "sub": "john",
         *     "authorities": ["ROLE_USER"]
         * }
         */
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer(issuer)
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(authentication.getName())
                .claim("authorities", authorities)
                .build();

        // Encode and sign the JWT using the configured JwtEncoder.
        Jwt jwt = jwtEncoder.encode(
                JwtEncoderParameters.from(claims)
        );

        // Return only the token string to the client.
        return jwt.getTokenValue();
    }
}