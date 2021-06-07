pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
            // Get some code from a GitHub repository
            git branch: 'main', url: 'https://github.com/cristobal-tello/spring-petclinic.git'
            }
        }
        
        stage('Build') {
            steps {
                bat "mvn clean package"

                // Run Maven on a Unix agent.
                //sh "mvn -Dmaven.test.failure.ignore=true clean package"

                // To run Maven on a Windows agent, use
                // bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }

            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                always {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                    emailext subject: "Job \'${JOB_NAME}\' (build ${BUILD_NUMBER}) ${currentBuild.result}",
                        body:'Please open {BUILD_URL} and check the build.',
                        attachLog: true,
                        compressLog: true,
                        to: "cristobaltello.es@gmail.com",
                        recipientProviders: [upstreamDevelopers(), requestor()]
                }
            }
        }
    }
}
