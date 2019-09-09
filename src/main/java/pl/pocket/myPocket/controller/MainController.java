package pl.pocket.myPocket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.pocket.myPocket.controller.repository.UserRepository;
import pl.pocket.myPocket.model.RegistrationForm;
import pl.pocket.myPocket.model.Session;
import pl.pocket.myPocket.model.User;

import javax.transaction.Transactional;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Session session;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @RequestMapping("/loggedin")
    public String loggedin(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName;
        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        User userFromRepository = userRepository.getUserFromRepository(userName);
        session.setUser(userFromRepository);
        return app(model);
    }

    @RequestMapping("/app")
    public String app(Model model) {
        User user = session.getUser();
        if (user == null) return "/login?error=nouser";
        model.addAttribute("user", user);
        return "app/app.html";
    }

    @RequestMapping("/login")
    public String loginPage(Model model) {
        RegistrationForm registrationForm = new RegistrationForm();
        model.addAttribute("registrationForm", registrationForm);
        return "login.html";
    }

    @RequestMapping(value = "/registration")
    @Transactional
    public String register(RegistrationForm registrationForm, Model model) {
        boolean userExists = userRepository.checkIfUserExists(registrationForm.getUserName());
        boolean passwordLength = registrationForm.getPassword().length() >= 8;
        boolean passwordConfirmed = registrationForm.getPassword().equals(registrationForm.getPasswordConfirmation());
        if (!userExists && passwordLength && passwordConfirmed) {
            try {
                User user = new User(registrationForm.getUserName(), passwordEncoder.encode(registrationForm.getPassword()));
                userRepository.persistUser(user);
                model.addAttribute("registrationPassed", "yes");
                return "/login";
            } catch (Exception e) {
                model.addAttribute("registrationPassed", "no");
                return "login.html";
            }
        } else {
            model.addAttribute("registrationPassed", "no");
            return "login.html";
        }
    }

    @RequestMapping(value = "/isusernameenabled")
    @ResponseBody
    public String isUsernameEnabled(@RequestParam(name = "username") String userName) {
        boolean exists = userRepository.checkIfUserExists(userName);
        if (exists) return "exists";
        else return "free";
    }

}
