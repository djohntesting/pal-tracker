package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
    String helloMessage;

    @GetMapping("/")
    public String sayHello() {
        return helloMessage;
    }

    public WelcomeController(@Value("${welcome.message}") String message){
        this.helloMessage = message;
    }

}