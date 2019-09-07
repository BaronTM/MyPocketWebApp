package pl.pocket.myPocket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.pocket.myPocket.controller.repository.UserRepository;
import pl.pocket.myPocket.model.RegistrationForm;
import pl.pocket.myPocket.model.Session;
import pl.pocket.myPocket.model.User;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Session session;

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
    public String loginPage() {
        return "login.html";
    }

    @RequestMapping(value = "/registration")
    public String register(RegistrationForm registrationForm) {
        System.out.println(registrationForm.getUserName());
        System.out.println(registrationForm.getPassword());
        System.out.println(registrationForm.getPasswordConfirmation());
        return "login.html";
    }

}
