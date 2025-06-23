    package cl.duoc.integracion.apiwebservice.Security;

    import org.springframework.security.config.Customizer;


    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.security.web.SecurityFilterChain;

    @Configuration
    @EnableWebSecurity
    public class SeguridadConfig {

        @Bean
        SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
            http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                    .requestMatchers(HttpMethod.GET,"/api/productos").permitAll()
                    .requestMatchers(HttpMethod.POST,"/api/productos/**").hasRole("Administrador")
                    .requestMatchers(HttpMethod.PUT,"/api/productos/**").hasRole("Administrador")
                    .requestMatchers(HttpMethod.PATCH,"/api/productos/**").hasRole("Administrador")
                    .requestMatchers(HttpMethod.DELETE,"/api/productos/**").hasRole("Administrador")
                    .requestMatchers("/api/empleados/**").hasRole("Administrador")
                    .requestMatchers("/api/clientes/**").hasRole("Administrador")
                    .requestMatchers("/swagger-ui/index.html").hasRole("Administrador")
                    .requestMatchers("/swagger-ui/v3/api-docs").hasRole("Administrador")
                    .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());
            
            return http.build();
        }

        @Bean
        PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
        }
    }
