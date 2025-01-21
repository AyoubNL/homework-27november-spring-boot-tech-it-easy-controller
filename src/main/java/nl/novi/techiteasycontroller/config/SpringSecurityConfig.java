package nl.novi.techiteasycontroller.config;

import nl.novi.techiteasycontroller.filter.JwtRequestFilter;
import nl.novi.techiteasycontroller.service.CustomUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    public final CustomUserDetailService customUserDetailService;

    private final JwtRequestFilter jwtRequestFilter;

    private final PasswordEncoder passwordEncoder;

    public SpringSecurityConfig(CustomUserDetailService customUserDetailService, JwtRequestFilter jwtRequestFilter, PasswordEncoder passwordEncoder) {
        this.customUserDetailService = customUserDetailService;
        this.jwtRequestFilter = jwtRequestFilter;
        this.passwordEncoder = passwordEncoder;
    }


    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(customUserDetailService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();

    }

        @Bean
        protected SecurityFilterChain filter (HttpSecurity http) throws Exception {

            http
                    .csrf(csrf -> csrf.disable())
                    .httpBasic(basic -> basic.disable())
                    .cors(Customizer.withDefaults())
                    .authorizeRequests(auth -> auth
                            .requestMatchers(HttpMethod.POST, "/users").permitAll()
                            .requestMatchers(HttpMethod.GET, "/users/**").permitAll()
                            .requestMatchers(HttpMethod.POST, "/users/**").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.POST, "/cimodules").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.DELETE, "/cimodules/**").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.POST, "/remotecontrollers").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.DELETE, "/remotecontrollers/**").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.POST, "/televisions").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.GET, "/televisions").hasRole("USER")
                            .requestMatchers(HttpMethod.DELETE, "/televisions/**").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.POST, "/wallbrackets").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.DELETE, "/wallbrackets/**").hasRole("ADMIN")
                            .requestMatchers("/cimodules", "/remotecontrollers", "/televisions", "/wallbrackets").hasAnyRole("ADMIN", "USER")
                            .requestMatchers("/authenticated").authenticated()
                            .requestMatchers("/authenticate").permitAll()
                            .anyRequest().denyAll()
                    ).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
            http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

            return http.build();

        }


}
