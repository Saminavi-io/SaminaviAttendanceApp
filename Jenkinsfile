pipeline {
    agent any

        options {
        timestamps()               // Adds timestamps to console output
        ansiColor('xterm')         // Enables colored output
        logRotator(
            daysToKeepStr: '10',
            numToKeepStr: '5',
            artifactDaysToKeepStr: '5',
            artifactNumToKeepStr: '2'
        )                          // Cleans up logs and artifacts
    }

    // Environment variables
    environment {
        DOCKER_REGISTRY = 'pramilay/attendanceportal'
        DOCKER_IMAGE = 'pramilay/attendanceportal:latest'
        GIT_CREDENTIALS_ID = 'github-credentials' 
        DOCKER_CREDENTIALS_ID = 'docker-credentials' 
    }

    triggers {
        // Automatically trigger the pipeline on code push
        pollSCM('')
        // Use GitHub webhooks for real-time triggering if configured
         githubPush()
    }

    stages {
        stage('Check') {
            steps {
                sh 'echo "Hello World"'
            }
        }
        stage('Checkout Code') {
            steps {
                echo 'Checking out the latest code from GitHub...'
                git branch: 'next',
                    url: 'git@github.com:PramilaSaminavi/https://github.com/Saminavi-io/SaminaviAttendanceApp.git',
                    credentialsId: "${github-credentials}"
            }
        }

        stage('Build') {
            steps {
                echo 'Building the application...'
                sh '''
                	mvn clean package
                	mvn package -DskipTests
                	mvn package -X
                ''' 
            }
        }

        stage('Test') {
            steps {
                echo 'Running application tests...'
                sh './test.sh' // Replace with your test suite command
            }
        }

        stage('Containerize') {
            steps {
                echo 'Building Docker container...'
                script {
                    docker.build("${DOCKER_IMAGE}:${BUILD_NUMBER}")
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                echo 'Pushing Docker image to registry...'
                script {
                    docker.withRegistry("https://${DOCKER_REGISTRY}", "${docker-credentials}") {
                        docker.image("${DOCKER_IMAGE}:${BUILD_NUMBER}").push()
                        docker.image("${DOCKER_IMAGE}:${BUILD_NUMBER}").push('latest')
                    }
                }
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying Docker container to server...'
                sh """
                docker pull ${DOCKER_IMAGE}:${BUILD_NUMBER}
                docker stop app || true
                docker rm app || true
                docker run -d --name app -p 9090:8080 ${DOCKER_IMAGE}:${BUILD_NUMBER}
                """
            }
        }
    }

    post {
        always {
            echo 'Pipeline completed.'
        }
        success {
            echo 'Pipeline succeeded!'
        }
        failure {
            echo 'Pipeline failed.'
            mail to: 'pramilay@saminavi.io',
                subject: "Jenkins Pipeline Failed: ${env.JOB_NAME} - Build #${env.BUILD_NUMBER}",
                body: "Something went wrong in build ${env.BUILD_URL}. Check the logs for details."
        }
        options {
            timestamps()
            ansiColor('xterm')
            logRotator(daysToKeepStr: '10')
}
    }
}