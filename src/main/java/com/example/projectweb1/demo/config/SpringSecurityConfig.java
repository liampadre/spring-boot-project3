package com.example.projectweb1.demo.config;

import com.example.projectweb1.demo.filter.JWTAuthenticationFilter;
import com.example.projectweb1.demo.filter.JWTAuthorizationFilter;
import com.example.projectweb1.demo.handler.LoginSuccessHandler;
import com.example.projectweb1.demo.service.JWTService;
import com.example.projectweb1.demo.service.LocalUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private LocalUserDetailsService userDetailsService;

    @Autowired
    private JWTService jwtService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

// Config in memory of users and roles
//    @Autowired
//    public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception {
//        PasswordEncoder encoder = passwordEncoder();
//        User.UserBuilder userBuilder = User.builder().passwordEncoder(password -> encoder.encode(password));
//
//        builder.inMemoryAuthentication()
//                .withUser(userBuilder.username("jovicafe").password("jovi87").roles("CLIENT", "USER"))
//                .withUser(userBuilder.username("liampadre").password("liam82").roles("CLIENT", "ADMIN"));
//    }

// Config in ddbb with queries
//    @Autowired
//    public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception {
//        builder.jdbcAuthentication()
//                .dataSource(dataSource)
//                .passwordEncoder(passwordEncoder())
//                .usersByUsernameQuery("SELECT username, password, enabled " +
//                        "FROM users WHERE username = ?")
//                .authoritiesByUsernameQuery("SELECT u.username, a.authority " +
//                        "FROM authorities a INNER JOIN users u ON (a.user_id = u.id) " +
//                        "WHERE u.username = ?");
//    }

    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    // All this configuration is commented because we going to implemented a stateless rest api project using security with JWT
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers("/", "/persons/**", "/api/**").permitAll()
//                // This is commented because role validations is implemented directly in controllers by annotations
////                .antMatchers("/players/**").hasAnyRole("USER")
////                .antMatchers("/clients/**").hasAnyRole("CLIENT")
////                .antMatchers("/employees/**").hasAnyRole("ADMIN")
////                .antMatchers("/employees").hasAnyRole("ADMIN")
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().successHandler(loginSuccessHandler).loginPage("/login").permitAll()
//                // If path != /login then go to path, else go to /clients
////                .defaultSuccessUrl("/clients")
//                // Always go to /clients
////                .defaultSuccessUrl("/clients", true)
//                // If this property is not defined execute onAuthenticationSuccess(...) in loginSuccessHandler
//                .and()
//                .logout().permitAll()
//                .and()
//                .exceptionHandling().accessDeniedPage("/error_403");
//    }

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/", "/persons/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtService))
                .addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtService))
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}