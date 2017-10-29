package net.cyphoria.cylus.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Stefan Pennndorf
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping
    public String login() {
        return "login";
    }
}
