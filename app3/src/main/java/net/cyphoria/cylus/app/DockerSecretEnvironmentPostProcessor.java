package net.cyphoria.cylus.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.logging.DeferredLog;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

/**
 * @author Stefan Pennndorf
 */
public class DockerSecretEnvironmentPostProcessor implements EnvironmentPostProcessor,SpringApplicationRunListener,  ApplicationListener<ApplicationPreparedEvent> {

    private static final Charset UTF_8 = Charset.forName("UTF8");
    private static final Predicate<Secret> IS_NOT_NULL = ((Predicate<Secret>) Objects::isNull).negate();

    private static final DeferredLog LOGGER = new DeferredLog();

    @Override
    public void postProcessEnvironment(final ConfigurableEnvironment environment,
                                       final SpringApplication application) {


        // /run/secrets/postgres-super-pwd
        try {
            final long numberOfSecrets = Files.list(Paths.get("/run/secrets/")).count();
            final String summary = String.format(
                    "There are %d secrets available",
                    numberOfSecrets
            );
            LOGGER.info(summary);

            if (numberOfSecrets > 0L) {

                final Map<String, Object> secrets = Files.list(Paths.get("/run/secrets/"))
                        .filter(DockerSecretEnvironmentPostProcessor::isNoValidSecret)
                        .map(DockerSecretEnvironmentPostProcessor::toSecret)
                        .filter(IS_NOT_NULL)
                        .peek(s -> LOGGER.info("Found secret: " + s.key))
                        .collect(Collectors.toMap(s -> s.key, s -> s.content));

                final MapPropertySource mps = new MapPropertySource("docker-secrets", secrets);

                environment.getPropertySources().addFirst(mps);
            }

        } catch (final NoSuchFileException ignored) {
            // No Secrets available at all configured
            LOGGER.info("Directory /run/secrets/ does not exist: No secrets configured.");

        } catch (final IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private static boolean isNoValidSecret(final Path path) {
        return Files.isReadable(path) && !Files.isDirectory(path);
    }

    private static Secret toSecret(final Path path) {

        final String fileName = Objects.toString(path.getFileName(), null);

        // if path is directory root
        if (fileName == null) {
            final String errorMessage = String.format(
                    "Unable to read secret at '%s' - is there a link to directory root?",
                    path
            );
            LOGGER.error(errorMessage);
            return null;
        }

        try {
            final String key = "secrets." + fileName;
            final String secretInfo = String.format(
                    "Reading secret '%s' from '%s'",
                    key,
                    path);
            LOGGER.debug(secretInfo);

            final String content = Files.readAllLines(path, UTF_8)
                    .stream()
                    .collect(joining("\n"));

            content.chars()
                .forEach(c -> System.err.print(c));
            System.err.println();
            System.err.println(content);


            return new Secret(key, content);
        } catch (final IOException e) {
            LOGGER.error("Unable to read secret at '" + path + '\'', e);
            System.err.println("unable to read secret");
        }

        return null;
    }

    @Override
    public void onApplicationEvent(final ApplicationPreparedEvent event) {
        //LOGGER.replayTo(DockerSecretEnvironmentPostProcessor.class);
    }

    @Override
    public void starting() {

    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {

    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {

    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        LOGGER.replayTo(DockerSecretEnvironmentPostProcessor.class);
    }

    @Override
    public void finished(ConfigurableApplicationContext context, Throwable exception) {

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
