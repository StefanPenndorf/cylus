package net.cyphoria.cylus.app.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Stefan Pennndorf
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(KontoController.class)
class KontoControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    void findetAlleKontenOhneSuchbegriff() throws Exception {
        mvc.perform(get("/konto/search")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .param("term", ""))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].id", is("1230")))
                .andExpect(jsonPath("[0].value", is("1230")))
                .andExpect(jsonPath("[0].label", is("1230 - ABC")))
                .andExpect(jsonPath("[1].id", is("1010")))
                .andExpect(jsonPath("[1].value", is("1010")))
                .andExpect(jsonPath("[1].label", is("1010 - Spesen")))
        ;
    }

    @Test
    void findetKontenNachKontonummerAnfangAlsSuchbegriff() throws Exception {
        mvc.perform(get("/konto/search")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .param("term", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].id", is("1010")))
                .andExpect(jsonPath("[0].value", is("1010")))
                .andExpect(jsonPath("[0].label", is("1010 - Spesen")))
        ;
    }

}