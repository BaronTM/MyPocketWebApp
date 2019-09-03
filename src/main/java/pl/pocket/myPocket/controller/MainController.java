package pl.pocket.myPocket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/wallet")
    public String wallet() {
        return "hello.html";
    }
}
