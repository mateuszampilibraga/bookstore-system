package one.digitalinnovation.bookstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .requestMatchers("/h2-console/**").permitAll()  // Permitir acesso ao console H2 sem autenticação
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .csrf().disable()  // Desabilitar CSRF para o console H2
                .headers().frameOptions().disable();  // Habilitar o frame do console H2

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetailsService userDetailsService = new InMemoryUserDetailsManager();
        ((InMemoryUserDetailsManager) userDetailsService).createUser(User.withUsername("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("USER")
                .build());
        return userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
