package net.cyphoria.cylus.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.function.Predicate;

import static java.util.stream.Collectors.joining;

/**
 * @author Stefan Pennndorf
 */
public class DockerSecretEnvironmentPostProcessor implements EnvironmentPostProcessor {

    private static final Charset UTF_8 = Charset.forName("UTF8");
    private static final Logger LOGGER = LoggerFactory.getLogger(DockerSecretEnvironmentPostProcessor.class);
    private static final Predicate<Secret> IS_NOT_NULL = ((Predicate<Secret>) Objects::isNull).negate();

    @Override
    public void postProcessEnvironment(final ConfigurableEnvironment environment,
                                       final SpringApplication application) {


        // /run/secrets/postgres-super-pwd
        try {
            LOGGER.info("There are {} secrets available", Files.list(Paths.get("/run/secrets/")).count());
            Files.list(Paths.get("/run/secrets/"))
                    .filter(DockerSecretEnvironmentPostProcessor::isNoValidSecret)
                    .map(DockerSecretEnvironmentPostProcessor::toSecret)
                    .filter(IS_NOT_NULL)
                    .forEach(secret -> System.out.println(secret));

        } catch (final NoSuchFileException e) {
            // No Secrets available at all configured
            LOGGER.info("Directory /run/secrets/ does not exist: No secrets configured.");
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private static boolean isNoValidSecret(final Path path) {
        return Files.isReadable(path) && !Files.isDirectory(path);
    }

    private static Secret toSecret(final Path path) {

        final String key = Objects.toString(path.getFileName(), null);

        // if path is directory root
        if (key == null) {
            LOGGER.debug("Unable to read secret at '{}' - is there a link to directory root?", path);
            return null;
        }

        try {
            LOGGER.debug("Reading secret '{}' from '{}'", key, path);

            final String content = Files.readAllLines(path, UTF_8)
                    .stream()
                    .collect(joining("\n"));

            return new Secret(key, content);
        } catch (final IOException e) {
            LOGGER.debug("Unable to read secret at '" + path + '\'', e);
        }

        return null;
    }

    private static class Secret {
        private final String key;
        private final String content;

        private Secret(final String key, final String content) {
            this.key = key;
            this.content = content;
        }
    }
}
