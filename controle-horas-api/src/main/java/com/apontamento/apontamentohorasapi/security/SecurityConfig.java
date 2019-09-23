package com.apontamento.apontamentohorasapi.security;

import com.apontamento.apontamentohorasapi.security.jwt.JwtSecurityConfigurer;
import com.apontamento.apontamentohorasapi.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // TODO Corrigir permissões de acesso a API
        http.httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/auth/signin").permitAll()
//                .antMatchers(HttpMethod.GET, "/projetos/user/*").permitAll()
//                .antMatchers(HttpMethod.DELETE, "/projetos/**").hasRole("ROLE_ADMIN")
                .antMatchers(HttpMethod.GET, "/projetos").permitAll()
                .antMatchers(HttpMethod.POST, "/apontamento-horas").permitAll()
                .anyRequest().authenticated()
                .and()
                .apply(new JwtSecurityConfigurer(jwtTokenProvider));
    }
}
