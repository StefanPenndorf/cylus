package net.cyphoria.cylus.app;

import net.cyphoria.cylus.app.domain.Buchung;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackageClasses = Buchung.class)
public class Cylus3 {

    public static void main(final String[] args) {
        SpringApplication.run(Cylus3.class, args);
    }
}
