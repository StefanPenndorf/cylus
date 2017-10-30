package net.cyphoria.cylus.app.domain;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author Stefan Pennndorf
 */
@ToString
@Data
public class Memo {

    private @NotNull String text;
}
