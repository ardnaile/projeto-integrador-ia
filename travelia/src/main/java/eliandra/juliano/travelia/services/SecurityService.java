package eliandra.juliano.travelia.services;

import eliandra.juliano.travelia.models.Usuario;
import eliandra.juliano.travelia.repositories.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Service
public class SecurityService {
    // aqui criamos um filtro que intercepta as requisições e controla a autorizacao
    @Component
    public class SecurityFilter extends OncePerRequestFilter {
        @Autowired
        TokenService tokenService;
        @Autowired
        UsuarioRepository usuarioRepository;
        @Override
        protected void doFilterInternal(
                HttpServletRequest request,
                HttpServletResponse response,
                FilterChain filterChain)
                throws ServletException, IOException {String token = this.recoverToken(request);
            if (token != null) {
                String subject = tokenService.validateToken(token);
                Usuario user = usuarioRepository.findByUsername(subject).get();
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                user,
                                user.getAuthorities(),
                                user.getAuthorities()
                        );
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            filterChain.doFilter(request, response);}

        // esse método encapsula a extracao do token do cabecalho da requisicao
        private String recoverToken(HttpServletRequest request) {
            String authHeader = request.getHeader("Authorization");
            if (authHeader == null) return null;
            return authHeader.replace("Bearer "
                    ,
                    "");
        }
    }

    // essa classe configura o filtro recem criado
    // especificamos que todas as rotas /auth serão permitidas à usuários não logados, as get serão para usuários autenticados
    @Configuration
    @EnableWebSecurity
    public class SecurityConfig {
        @Autowired
        SecurityFilter securityFilter;
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)
                throws Exception {return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS))
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/auth/*").permitAll()
                        .requestMatchers(HttpMethod.GET,
                                "/").authenticated()
//                        .requestMatchers(HttpMethod.POST,
//                                "/").hasAuthority("ROLE_MANAGER")
//                        .anyRequest().hasAuthority("ROLE_ADMIN")
                )
                .build();
        }
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
