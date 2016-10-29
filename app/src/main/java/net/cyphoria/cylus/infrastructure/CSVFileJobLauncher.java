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

import net.cyphoria.cylus.buchungsimport.UploadedFileKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.integration.launch.JobLaunchRequest;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.integration.annotation.Transformer;
import org.springframework.stereotype.Component;

/**
 * @author Stefan Pennndorf
 */
@Component
public class CSVFileJobLauncher {

    private final Logger logger = LoggerFactory.getLogger(CSVFileJobLauncher.class);

    public CSVFileJobLauncher() {
        super();
    }

    @Transformer(inputChannel = "csvFileInputChannel", outputChannel = "jobChannel")
    public JobLaunchRequest transform(final UploadedFileKey file) throws Exception {
        logger.info("Creating request");

        final JobParameters jobParameters = new JobParameters();
        new JobParametersBuilder().addParameter("csvFile", new JobParameter(file.toString()));
        JobLaunchRequest request = new JobLaunchRequest(fileToDatabaseJob(), jobParameters);

        return request;

    }


    @Lookup
    public Job fileToDatabaseJob() {
        return null;
    }

}
