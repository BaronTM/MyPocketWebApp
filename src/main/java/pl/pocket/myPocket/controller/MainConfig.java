package pl.pocket.myPocket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.pocket.myPocket.controller.repository.UserRepository;

import javax.annotation.PostConstruct;

@Configuration
@ComponentScan(basePackages = "pl.pocket.myPocket")
@EntityScan(basePackages = "pl.pocket.myPocket")
public class MainConfig {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
