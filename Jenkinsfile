node '', {
    def mvnHome
    stage('Preparation') { // for display purposes
        // Get some code from a GitHub repository
        git branch: "${GIT_BRANCH}", url: 'https://github.com/KyleRogers/cylus.git'
        // Get the Maven tool.
        // ** NOTE: This 'M3' Maven tool must be configured
        // **       in the global configuration.
        mvnHome = tool 'M3'
    }
    stage('Build') {
        dir 'app3', {
            // Run the maven build
            sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean package"
        }
    }
    stage('Results') {
        dir 'app3', {
            junit '**/target/surefire-reports/TEST-*.xml'
            archive 'target/*.jar'
        }
    }
}