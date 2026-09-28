package org.backend.smallecommerceapi.config;

import org.backend.smallecommerceapi.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * Configures Spring Security for the application.
 *
 * <p>This configuration handles:</p>
 * <ul>
 *     <li>Password encryption using BCrypt</li>
 *     <li>Username/password authentication during login</li>
 *     <li>JWT creation and validation</li>
 *     <li>JWT authority conversion</li>
 *     <li>API endpoint authorization</li>
 *     <li>Stateless session management</li>
 * </ul>
 */
@Configuration
public class SecurityConfig {

    /**
     * Creates the password encoder used to hash and verify user passwords.
     *
     * @return BCrypt password encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Creates the authentication manager used to authenticate users
     * during the login process.
     *
     * @param daoAuthenticationProvider provider responsible for
     *                                  username/password authentication
     * @return authentication manager
     */
    @Bean
    public AuthenticationManager authenticationManager(
            DaoAuthenticationProvider daoAuthenticationProvider
    ) {
        return new ProviderManager(daoAuthenticationProvider);
    }

    /**
     * Configures username/password authentication using
     * {@link CustomUserDetailsService}.
     *
     * <p>The user details service loads the user from the database,
     * while the password encoder verifies the submitted password.</p>
     *
     * @param customUserDetailsService service used to load users
     * @param passwordEncoder encoder used to verify passwords
     * @return configured authentication provider
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider(
            CustomUserDetailsService customUserDetailsService,
            PasswordEncoder passwordEncoder
    ) {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider(customUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder);

        return provider;
    }

    /**
     * Configures how authorities are extracted from JWT claims.
     *
     * <p>The application stores authorities in the JWT using the
     * {@code authorities} claim. No additional authority prefix
     * is added.</p>
     *
     * @return JWT authentication converter
     */
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {

        JwtGrantedAuthoritiesConverter authoritiesConverter =
                new JwtGrantedAuthoritiesConverter();
        authoritiesConverter.setAuthoritiesClaimName("authorities");
        authoritiesConverter.setAuthorityPrefix("");

        JwtAuthenticationConverter converter =
                new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(authoritiesConverter);

        return converter;
    }

    /**
     * Creates the secret key used for signing and verifying JWTs.
     *
     * <p>The secret is loaded from the {@code jwt.secret} application
     * property and decoded from Base64.</p>
     *
     * @param secret Base64-encoded JWT secret
     * @return secret key used with HMAC-SHA256
     */
    @Bean
    public SecretKey secretKey(
            @Value("${jwt.secret}") String secret
    ) {
        byte[] decodedKey = Base64.getDecoder().decode(secret);

        return new SecretKeySpec(decodedKey, "HmacSHA256");
    }

    /**
     * Creates the JWT encoder used to generate signed access tokens.
     *
     * <p>The application uses the HS256 algorithm to sign JWTs.</p>
     *
     * @param secretKey secret key used to sign the JWT
     * @return JWT encoder
     */
    @Bean
    public JwtEncoder jwtEncoder(SecretKey secretKey) {
        return NimbusJwtEncoder
                .withSecretKey(secretKey)
                .algorithm(MacAlgorithm.HS256)
                .build();
    }

    /**
     * Creates the JWT decoder used to validate incoming access tokens.
     *
     * <p>The decoder verifies the JWT signature and validates the issuer
     * and standard JWT claims such as expiration.</p>
     *
     * @param secretKey secret key used to verify the JWT
     * @param issuer expected JWT issuer
     * @return JWT decoder
     */
    @Bean
    public JwtDecoder jwtDecoder(
            SecretKey secretKey,
            @Value("${jwt.issuer}") String issuer
    ) {
        NimbusJwtDecoder decoder = NimbusJwtDecoder
                .withSecretKey(secretKey)
                .macAlgorithm(MacAlgorithm.HS256)
                .build();

        decoder.setJwtValidator(
                JwtValidators.createDefaultWithIssuer(issuer)
        );

        return decoder;
    }

    /**
     * Configures the application's HTTP security rules.
     *
     * <p>The API uses stateless JWT authentication. Authentication
     * endpoints are publicly accessible, admin endpoints require
     * the ADMIN role, and all other endpoints require authentication.</p>
     *
     * @param http Spring Security HTTP security configuration
     * @param provider username/password authentication provider
     * @param jwtAuthenticationConverter converter for JWT authorities
     * @return configured security filter chain
     */
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            DaoAuthenticationProvider provider,
            JwtAuthenticationConverter jwtAuthenticationConverter
    ) {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authenticationProvider(provider)
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers("/api/v1/auth/**").permitAll()
                                .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                )
                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )
                .oauth2ResourceServer(oauth2 ->
                        oauth2.jwt(jwt ->
                                jwt.jwtAuthenticationConverter(
                                        jwtAuthenticationConverter
                                )
                        )
                );

        return http.build();
    }
}