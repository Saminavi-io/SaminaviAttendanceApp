pipeline {
    agent any 

    tools {
        maven 'maven3'  // Must match the name you configured in step 4.1
    }

    options {
        // For log rotation, use buildDiscarder instead of logRotator directly
        buildDiscarder(logRotator(
            daysToKeepStr: '10',
            numToKeepStr: '5',
            artifactDaysToKeepStr: '5',
            artifactNumToKeepStr: '2'
        ))
        // Use timestamps option directly - this is a valid option
        timestamps()
        // Remove ansiColor from options block
    }

    // Environment variables
    environment {
        DOCKER_REGISTRY = 'pramilay/attendanceportal'
        DOCKER_IMAGE = 'pramilay/attendanceportal:latest'
        DOCKER_CREDENTIALS_ID = 'docker-credentials' 
        GIT_REPO = 'https://github.com/Saminavi-io/SaminaviAttendanceApp.git'
        GIT_BRANCH = 'next'
         GIT_CREDENTIALS_ID = 'github-credentials' 
    }

    triggers {
        // Automatically trigger the pipeline on code push
        pollSCM('H/15 * * * *')
        // Use GitHub webhooks for real-time triggering if configured
        // githubPush()
    }

    stages {
        stage('Check') {
            steps {
                sh 'echo "Hello World"'
            }
        }
        stage('Checkout Code') {
            steps {
                // For colored output, wrap the steps in ansiColor
                wrap([$class: 'AnsiColorBuildWrapper', 'colorMapName': 'xterm']) {
                echo 'Checking out the latest code from GitHub...'
                    // Use withCredentials to securely access GitHub
                    withCredentials([usernamePassword(credentialsId: "${GIT_CREDENTIALS_ID}", 
                                                    usernameVariable: 'GIT_USER', 
                                                    passwordVariable: 'GIT_PASS')]) {
                        // Either use this explicit checkout
                        // checkout([$class: 'GitSCM', 
                        //         branches: [[name: "*/${GIT_BRANCH}"]], 
                        //         userRemoteConfigs: [[url: "${GIT_REPO}", 
                        //                             credentialsId: "${GIT_CREDENTIALS_ID}"]]])
                        
                        // Or if you prefer to use the implicit SCM checkout
                        checkout scm: [
                            $class: 'GitSCM',
                            branches: [[name: "*/${GIT_BRANCH}"]],
                            doGenerateSubmoduleConfigurations: false,
                            extensions: [],
                            userRemoteConfigs: [[
                                url: "${GIT_REPO}",
                                credentialsId: "${GIT_CREDENTIALS_ID}"
                            ]]
                        ]
                    }
                }    
            }
        }

        stage('Build') {
            steps {
                echo 'Building the application...'
                    withMaven(maven: 'maven3') {  // Must match your Maven tool name
                        sh '''
                            mvn clean package
                            mvn package -DskipTests
                            mvn package -X
                        ''' 
                    }
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
            // mail to: 'pramilay@saminavi.io',
            //     subject: "Jenkins Pipeline Failed: ${env.JOB_NAME} - Build #${env.BUILD_NUMBER}",
            //     body: "Something went wrong in build ${env.BUILD_URL}. Check the logs for details."
        }
    }
}