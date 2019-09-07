package pl.pocket.myPocket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

@Configuration
@EnableWebMvc
public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer{

    @Autowired
    private DataSource dataSource;

    @Override
    public void configure(HttpSecurity security) throws Exception {
        security.authorizeRequests()
                .antMatchers("/app/**").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/css/**", "/js/**", "/libs/**", "/pic/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/app", true)
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login");
    }

    @Autowired
    public void securityUser(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT user_name, user_password, enabled FROM user WHERE user_name=?")
                .authoritiesByUsernameQuery("SELECT u.user_name, r.role_name FROM user as u, role as r WHERE u.id_user = r.id_user AND u.user_name=?");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
                "/pic/**",
                "/css/**",
                "/libs/**",
                "/app/**",
                "/js/**")
                .addResourceLocations(
                        "classpath:/view/pic/",
                        "classpath:/view/css/",
                        "classpath:/view/libs/",
                        "classpath:/view/app/",
                        "classpath:/view/js/");
    }


}
