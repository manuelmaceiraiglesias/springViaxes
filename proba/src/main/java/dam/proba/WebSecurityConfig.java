package dam.proba;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder authBuilder) throws Exception {
        authBuilder.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(new BCryptPasswordEncoder()) //eso garda as pass cifradas
                .usersByUsernameQuery("select username, password, enabled from users where username=?")
                .authoritiesByUsernameQuery("select username, role from users where username=?")
        ;
    }


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { //se non cerro e abro o navegador queda logeado e autenticado
        http.authorizeHttpRequests(auth -> auth
                       .anyRequest().authenticated())  //est seria para pedir request a todo dios pero incluira tamen o rgister
               // .requestMatchers("/viajes").permitAll()) //esto prmitiria que nese endpoint non se pedise login
                //.requestMatchers("/users").hasRole("ADMIN")  //esto pediia login nese punto e eermitira admins
                .formLogin(login -> login.permitAll())
                .logout(logout -> logout.permitAll())
        ;

        return http.build();
    }
}