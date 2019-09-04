package pl.pocket.myPocket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/app")
    public String app() {
        return "app/app.html";
    }

    @RequestMapping("/login")
    public String loginPage() {
        return "login.html";
    }


}
