package net.cyphoria.cylus.app;

import net.cyphoria.cylus.app.domain.Buchung;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EntityScan(basePackageClasses = Buchung.class)
@EnableNeo4jRepositories
@EnableTransactionManagement
public class Cylus3 {

    public static void main(final String[] args) {
        SpringApplication.run(Cylus3.class, args);
    }
}
