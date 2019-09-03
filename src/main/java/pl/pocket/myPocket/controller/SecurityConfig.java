package pl.pocket.myPocket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity security) throws Exception {
        security.authorizeRequests()
                .antMatchers("/hello.html").hasAnyRole("USER", "ADMIN")
                .antMatchers("/login.html").permitAll()
                .anyRequest().authenticated()
                .and().formLogin()
                .loginPage("/login.html")
                .defaultSuccessUrl("/wallet");
    }

    @Autowired
    public void securityUser(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("ernest").password("{noop}1234").roles("ADMIN", "USER")
                .and()
                .withUser("user").password("{noop}user2").roles("USER");
    }
}
