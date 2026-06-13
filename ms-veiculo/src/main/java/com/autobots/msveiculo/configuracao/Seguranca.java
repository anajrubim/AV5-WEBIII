package com.autobots.msveiculo.configuracao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.autobots.msveiculo.filtros.Autorizador;
import com.autobots.msveiculo.jwt.ProvedorJwt;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Seguranca extends WebSecurityConfigurerAdapter {

    @Autowired
    private ProvedorJwt provedorJwt;

    private static final String ADMIN    = "ROLE_ADMIN";
    private static final String GERENTE  = "ROLE_GERENTE";
    private static final String VENDEDOR = "ROLE_VENDEDOR";
    private static final String CLIENTE  = "ROLE_CLIENTE";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        http.headers().frameOptions().disable();

        http.authorizeHttpRequests()
            .antMatchers("/h2-console/**").hasAnyAuthority(ADMIN)

            .antMatchers(HttpMethod.GET, "/veiculo/venda").hasAnyAuthority(ADMIN, GERENTE, VENDEDOR)

            .antMatchers(HttpMethod.GET,    "/veiculo", "/veiculo/**").hasAnyAuthority(ADMIN, GERENTE, VENDEDOR, CLIENTE)
            .antMatchers(HttpMethod.POST,   "/veiculo", "/veiculo/**").hasAnyAuthority(ADMIN, GERENTE, VENDEDOR)
            .antMatchers(HttpMethod.PUT,    "/veiculo", "/veiculo/**").hasAnyAuthority(ADMIN, GERENTE, VENDEDOR)
            .antMatchers(HttpMethod.DELETE, "/veiculo", "/veiculo/**").hasAnyAuthority(ADMIN, GERENTE)

            .anyRequest().authenticated();

        http.addFilter(new Autorizador(authenticationManager(), provedorJwt));

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource fonte = new UrlBasedCorsConfigurationSource();
        fonte.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return fonte;
    }
}
