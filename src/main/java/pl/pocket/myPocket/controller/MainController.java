package pl.pocket.myPocket.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {

    @RequestMapping({"/app"})
    public String hello() {
        return "hello world";
    }

}
