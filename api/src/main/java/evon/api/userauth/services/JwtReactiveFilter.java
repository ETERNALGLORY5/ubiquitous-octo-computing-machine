package evon.api.userauth.services;

//import jakarta.servlet.annotation.WebFilter;

//import jakarta.servlet.annotation.WebFilter;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
//import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class JwtReactiveFilter implements WebFilter {
    private static Logger logger = LoggerFactory.getLogger(JwtReactiveFilter.class);
    public static final String HEADER_PREFIX = "Bearer ";

    @Autowired
    JwtTokenService jwtTokenService;

    @Autowired
    AppUserService userService;

    public JwtReactiveFilter(JwtTokenService jwtTokenService,
                             AppUserService userService) {
        logger.info("Inside jwtTokenService: {}", jwtTokenService);
        this.jwtTokenService = jwtTokenService;
        this.userService = userService;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String token = resolveToken(exchange.getRequest());
        if (StringUtils.hasText(token)) {
            logger.info("Inside jwtTokenService: 1 {}", jwtTokenService);
            final String username = jwtTokenService.validateTokenAndGetUsername(token);
            if (username == null) {
//                logger.info("username not present");
                // validation failed or token expired
//                chain.doFilter(request, response);
                return chain.filter(exchange);
//                return;
            }
//            logger.info("In doFilterInternal 3");
            // set user details on spring security context

            return userService.findByUsername(username).flatMap(userDetails -> {
                final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
//                chain.filter(exchange).deferContextual(Context.of())
                return chain.filter(exchange).contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication));
            });

//            final Mono<UserDetails> userDetails = userService.findByUsername(username);
//            final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
//                    userDetails, null, userDetails.getAuthorities());
//
////            Authentication authentication = this.jwtTokenService.getAuthentication(token);
//            return chain.filter(exchange).subscriberContext(ReactiveSecurityContextHolder.withAuthentication(authentication));
        }
        return chain.filter(exchange);
    }
    private String resolveToken(ServerHttpRequest request) {
        String bearerToken = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(HEADER_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
