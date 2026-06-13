package com.autobots.apigateway.filtros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import com.autobots.apigateway.jwt.ProvedorJwt;

@Component
public class FiltroAutenticacao implements GlobalFilter, Ordered {

    @Autowired
    private ProvedorJwt provedorJwt;

    private boolean rotaPublica(ServerHttpRequest request) {
        String path = request.getURI().getPath();
        if (path.equals("/login")) {
            return true;
        }
        if (path.startsWith("/h2-console")) {
            return true;
        }
        return false;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        if (rotaPublica(request)) {
            return chain.filter(exchange);
        }

        String cabecalho = request.getHeaders().getFirst("Authorization");
        if (cabecalho == null || !cabecalho.startsWith("Bearer ")) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        String jwt = cabecalho.substring("Bearer ".length());
        if (!provedorJwt.validarJwt(jwt)) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
