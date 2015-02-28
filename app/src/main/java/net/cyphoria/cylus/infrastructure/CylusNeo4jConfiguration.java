/*
 * Copyright (c) Stefan Penndorf 2014
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.cyphoria.cylus.infrastructure;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;

/**
 * @author Stefan Pennndorf <stefan@cyphoria.net>
 */
@Configuration
@EnableNeo4jRepositories(basePackages = "net.cyphoria.cylus.domain.repositories")
@Profile("graph-store")
public class CylusNeo4jConfiguration extends Neo4jConfiguration {

    public CylusNeo4jConfiguration() {
        setBasePackage("net.cyphoria.cylus.domain");
    }


}
