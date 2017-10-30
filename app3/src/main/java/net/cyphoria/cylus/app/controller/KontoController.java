package net.cyphoria.cylus.app.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

/**
 * @author Stefan Pennndorf
 */
@Controller
@RequestMapping("/konto")
public class KontoController {

    @GetMapping(value = "search", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<KontoNamen> sucheNachId(@RequestParam final String term) {


        return Arrays.asList(
                new KontoNamen("1230", "1230", "1230 - ABC"),
                new KontoNamen("1010","1010", "1010 - Spesen")
        );
    }
}
