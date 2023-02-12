package ch.zhaw.fswd.backend.foodbase.security.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import ch.zhaw.fswd.backend.foodbase.security.TokenAuthenticationFilter;

@Configuration
public class TokenCookieAuthSecurityConfigAuth {

    @Value("${jwt.secret}")
    private String secret;

    @Bean
    @Order(3)
    public SecurityFilterChain filterChainAuthJWT(HttpSecurity http) throws Exception {
       //http.csrf().disable(); //hinzufügen-> Neuer Fehler
        http
                .antMatcher("/auth/**")
                .addFilterBefore(new TokenAuthenticationFilter(secret), UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .httpBasic().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/auth/**").permitAll()
                //.antMatchers("/auth/users").permitAll()//Fehler->rawpassword cannot be null
                .anyRequest().authenticated();
                
        return http.build();
    }

}