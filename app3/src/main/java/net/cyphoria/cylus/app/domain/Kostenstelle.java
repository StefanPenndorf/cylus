package net.cyphoria.cylus.app.domain;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author Stefan Pennndorf
 */
@Data
@ToString
public class Kostenstelle {

    private @NotNull Integer nummer;

    private @NotNull String name;

}
