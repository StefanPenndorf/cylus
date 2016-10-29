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

import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.integration.launch.JobLaunchingMessageHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.ExecutorChannel;

import java.util.concurrent.Executor;

/**
 * @author Stefan Pennndorf
 */
@IntegrationComponentScan(basePackages = "net.cyphoria.cylus")
@Configuration
public class IntegrationConfiguration {

    @Bean
    CSVFileJobLauncher csvFileJobLauncher() {
        return new CSVFileJobLauncher();
    }

    @Bean
    ExecutorChannel csvFileInputChannel(final Executor executor) {
        return new ExecutorChannel(executor);
    }

    @Bean
    @ServiceActivator(inputChannel = "jobChannel", outputChannel = "nullChannel")
    JobLaunchingMessageHandler launcher(final JobLauncher jobLauncher) {
        return new JobLaunchingMessageHandler(jobLauncher);
    }
}
