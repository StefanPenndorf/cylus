package net.cyphoria.cylus.integrationtest;/*
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

import com.google.common.collect.Lists;
import net.cyphoria.cylus.Cylus;
import net.cyphoria.cylus.domain.KontenArt;
import net.cyphoria.cylus.domain.repositories.KontenArtRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Stefan Penndorf
 */
@IntegrationTest
@ContextConfiguration(classes = Cylus.class, initializers = ConfigFileApplicationContextInitializer.class)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class KontenArtRepositoryIntegrationTest {

    @Autowired
    KontenArtRepository repository;

    @Test
    @Transactional
    @SuppressWarnings("unchecked")
    public void kannAlleKontenArtenLesen() {
        final List<KontenArt> all = Lists.newArrayList(repository.findAll());

        assertThat(all, hasSize(5));
    }

    @Test
    @Transactional
    public void kenntKontenArtAktiva() {
        final KontenArt aktiva = repository.findByName("Aktiva");

        assertThat(aktiva, is(notNullValue()));
        assertThat(aktiva.name, is("Aktiva"));

    }


}
