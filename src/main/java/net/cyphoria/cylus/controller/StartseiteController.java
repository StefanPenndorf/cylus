package net.cyphoria.cylus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Stefan Pennndorf <stefan@cyphoria.net>
 */
@Controller
public class StartseiteController {

    @RequestMapping("/")
    public String home() {
        return "startseite";
    }

}
