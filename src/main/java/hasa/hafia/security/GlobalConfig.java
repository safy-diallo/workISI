package hasa.hafia.security;

import hasa.hafia.exception.GlobalExceptionHandler;
import hasa.hafia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class GlobalConfig extends WebSecurityConfigurerAdapter {
    private UserService userService;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(e -> {
            e.antMatchers("/login", "/logout","/")
                        .permitAll()
                    .anyRequest().authenticated();
        });

        http.formLogin(e -> e.loginPage("/login")
                .successHandler(authenticationHandler())
                .loginProcessingUrl("/login")
                .failureForwardUrl("/login")
        );

        http.logout(e -> e.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .clearAuthentication(true)
        );

        http.exceptionHandling(e -> {
            e.accessDeniedHandler(new GlobalExceptionHandler());
        });

        http.csrf().disable().cors().disable();
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public SuccessAuthenticationHandler authenticationHandler(){
        return new SuccessAuthenticationHandler();
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
