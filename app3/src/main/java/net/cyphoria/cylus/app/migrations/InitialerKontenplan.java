package net.cyphoria.cylus.app.migrations;

import net.cyphoria.cylus.app.domain.KontenArt;
import net.cyphoria.cylus.app.domain.Konto;
import net.cyphoria.cylus.app.domain.KontoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Stefan Pennndorf
 */
@Component
public class InitialerKontenplan implements Migration {

    private final Logger logger = LoggerFactory.getLogger(InitialerKontenplan.class);

    private final KontoRepository kontoRepository;

    public InitialerKontenplan(final KontoRepository kontoRepository) {
        this.kontoRepository = kontoRepository;
    }


    @Override
    public void migrate() {
        final long count = kontoRepository.count();
        if (count > 0L) {
            logger.info("Überspringe Migration 'initialer Kontenplan': Es gibt bereits Konten");
            return;
        }

        final Konto k2231 = Konto.builder()
                .kontenArt(KontenArt.AKTIVA)
                .kontoName("DKB Giro")
                .kontonummer(2231)
                .build();
        final Konto k4101 = Konto.builder()
                .kontenArt(KontenArt.AUFWAND)
                .kontoName("Lebensmittel")
                .kontonummer(4101)
                .build();

        logger.info("Initialen Kontenplan anlegen:");
        kontoRepository.save(k2231);
        kontoRepository.save(k4101);
    }
}
