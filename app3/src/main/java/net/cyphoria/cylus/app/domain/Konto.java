package net.cyphoria.cylus.app.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import javax.validation.constraints.NotNull;

/**
 * @author Stefan Pennndorf
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NodeEntity
public class Konto {

    @Id
    @GeneratedValue
    private Long id;

    private @NotNull Integer kontonummer;

    private @NotNull String kontoName;

    private @NotNull KontenArt kontenArt;
}
