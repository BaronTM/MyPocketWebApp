package pl.pocket.myPocket.controller.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.pocket.myPocket.controller.repository.UserRepository;

import java.util.ArrayList;

@Service
public class PocketUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        pl.pocket.myPocket.model.entities.User userFromRepository = userRepository.getUserFromRepository(userName);
        return new User(userFromRepository.getUserName(), userFromRepository.getPassword(), new ArrayList<>());
    }
}
