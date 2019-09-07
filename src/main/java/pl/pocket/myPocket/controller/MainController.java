package pl.pocket.myPocket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.pocket.myPocket.model.Session;
import pl.pocket.myPocket.model.User;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Session session;


    @RequestMapping("/app")
    public String app(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName;
        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }

        User userFromRepository = userRepository.getUserFromRepository(userName);
        session.setUser(userFromRepository);
        if (userFromRepository == null) return "/login?error=nouser";
        model.addAttribute("user", session.getUser());
        return "app/app.html";
    }

    @RequestMapping("/login")
    public String loginPage() {
        return "login.html";
    }


}
