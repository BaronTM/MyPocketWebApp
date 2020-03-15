package pl.pocket.myPocket.controller;

import com.google.gson.Gson;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import pl.pocket.myPocket.controller.repository.UserRepository;
import pl.pocket.myPocket.model.entities.Account;
import pl.pocket.myPocket.model.entities.ExpenseCategory;
import pl.pocket.myPocket.model.Session;
import pl.pocket.myPocket.model.entities.RevenueCategory;
import pl.pocket.myPocket.model.entities.User;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Session session;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    private Gson gson;

    @GetMapping("/home")
    public String getUser() {
        return "czesc i czolem";
    }

    @GetMapping("/lgoin")
    public String login() {
        return "loggedIn";
    }

    @GetMapping("/")
    public String getString() {
        return "hello hello";
    }

}
