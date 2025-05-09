package co.ucentral.creditaplication.configurations;

import co.ucentral.creditaplication.utils.SecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class AuthConfig {
    SecurityFilter securityFilter;

    @Autowired
    public AuthConfig(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }
    private static final String ADMIN = "ADMIN";
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/*").permitAll()
                        .requestMatchers("/Cliente/*").permitAll()
                        .requestMatchers("/Empresa/*").permitAll()
                        .requestMatchers("/chat/*").permitAll()
                        .requestMatchers("/api/ofertas/*").permitAll()
                        .requestMatchers("/Credito/non-approved-credits").hasRole(AuthConfig.ADMIN)
                        .requestMatchers("/Credito/update-state").hasRole(AuthConfig.ADMIN)
                        .requestMatchers("/Credito/*").permitAll()
                        .requestMatchers("/Pagos/*").hasAnyRole(AuthConfig.ADMIN, "USER")
                        .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}