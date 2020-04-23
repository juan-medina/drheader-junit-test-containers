package com.medina.juan.drheaderjunittestcontainers;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class AdditionalHeaders implements WebFilter {
    @Override
    public Mono<Void> filter(final ServerWebExchange exchange, final WebFilterChain chain) {
        final HttpHeaders headers = exchange.getResponse().getHeaders();
        headers.add("Pragma", "no-cache");
        headers.add("Strict-Transport-Security", "max-age=31536000 ;includesubdomains");
        return chain.filter(exchange);
    }
}
