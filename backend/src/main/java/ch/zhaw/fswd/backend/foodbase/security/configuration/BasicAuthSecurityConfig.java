package ch.zhaw.fswd.backend.foodbase.security.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class BasicAuthSecurityConfig {
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public UserDetailsService jdbcUserDetailsService(DataSource dataSource) {
        String userQuery = "SELECT login_name, password_hash, true FROM login_info WHERE login_name=?";
        String roleQuery = "SELECT  LOGIN_NAME, ROLES_ROLE_NAME FROM LOGIN_INFO_ROLES JOIN LOGIN_INFO ON LOGIN_INFO_ID=ID WHERE LOGIN_NAME=?";
            
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);

        users.setUsersByUsernameQuery(userQuery);
        users.setAuthoritiesByUsernameQuery(roleQuery);

        return users;
    }

    @Bean
    @Order(2)
    public SecurityFilterChain filterChainAuthBasic(HttpSecurity http) throws Exception {
        http.antMatcher("/auth/token")
                    .httpBasic().and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                    .authorizeRequests()
                    .antMatchers(HttpMethod.OPTIONS,"/auth/token").permitAll()
                    .anyRequest().authenticated();
        return http.build();
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
