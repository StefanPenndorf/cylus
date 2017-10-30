package net.cyphoria.cylus.app.domain;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author Stefan Pennndorf
 */
@Data
@ToString
public class Konto {

    private @NotNull Integer kontonummer;

    private @NotNull String kontoName;

    private @NotNull KontenArt kontenArt;
}
