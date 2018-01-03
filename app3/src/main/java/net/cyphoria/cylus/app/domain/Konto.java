package net.cyphoria.cylus.app.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * @author Stefan Pennndorf
 */
@Data
@ToString
@Entity
public class Konto {

    @Id
    @GeneratedValue
    private Long id;

    private @NotNull Integer kontonummer;

    private @NotNull String kontoName;

    private @NotNull KontenArt kontenArt;
}
