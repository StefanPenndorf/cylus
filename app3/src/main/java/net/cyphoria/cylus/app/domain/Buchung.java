package net.cyphoria.cylus.app.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Stefan Pennndorf
 */
@Data
@ToString
@RequiredArgsConstructor
public class Buchung {

    private @NotNull LocalDate valutadatum;

    private @NotNull BigDecimal betrag;

    private @NotNull Konto konto;

    private @NotNull Konto gegenkonto;

    private @NotNull String buchungstext;

    private Kostenstelle kostenstelle;

    private Memo memo;


}
