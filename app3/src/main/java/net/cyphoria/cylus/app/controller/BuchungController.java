package net.cyphoria.cylus.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Stefan Pennndorf
 */
@Controller
@RequestMapping("/buchung")
public class BuchungController {

    @GetMapping("neu")
    public String neueBuchung() {
        return "buchungen/neu";
    }
}
