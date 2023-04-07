package evon.api.core.config;

import evon.api.userauth.services.JwtReactiveFilter;
import evon.api.userauth.services.JwtTokenService;
import evon.api.userauth.services.AppUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
//import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebFluxSecurity
public class ReactiveSecurityConfig {
    private static Logger logger = LoggerFactory.getLogger(ReactiveSecurityConfig.class);
    @Autowired
    AppUserService reactiveAppUserService;

    @Autowired
    JwtReactiveFilter jwtReactiveFilter;

    private CorsConfigurationSource corsConfigurationSource() {
        // Very permissive CORS config...
        final var configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setExposedHeaders(Arrays.asList("*"));

        // Limited to API routes (neither actuator nor Swagger-UI)
        final var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", configuration);

        return source;
    }

    @Bean
    public SecurityWebFilterChain filterWebChain(ServerHttpSecurity http,
                                                 JwtTokenService jwtTokenService) throws Exception {
        logger.info("INITIATE REACTIVE SECURITY CONFIG");
        return http
                .csrf(it -> it.disable())
                .cors(c -> c.configurationSource(corsConfigurationSource()))
                .httpBasic(it -> it.disable())
                .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
                .authorizeExchange(it ->
                                it
                                .pathMatchers( "/error").permitAll()
                                .pathMatchers( "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                                .pathMatchers(HttpMethod.POST, "/api/auth/**").permitAll()
                                .anyExchange().authenticated()
                )

                .addFilterAt(new JwtReactiveFilter(jwtTokenService, reactiveAppUserService), SecurityWebFiltersOrder.AUTHENTICATION)
                .build();
    }


    @Bean
    public ReactiveAuthenticationManager reactiveAuthenticationManager(ReactiveUserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        UserDetailsRepositoryReactiveAuthenticationManager authenticationManager = new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService);
        authenticationManager.setPasswordEncoder(passwordEncoder);
        return authenticationManager;
    }
}
