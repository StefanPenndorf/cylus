package net.cyphoria.cylus.app.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Stefan Pennndorf
 */
@Data
@AllArgsConstructor
public class KontoNamen {

    @JsonProperty("id")
    private String id;

    @JsonProperty("value")
    private String kontoNummer;

    @JsonProperty("label")
    private String kontoName;

}
