pipeline {
    agent any
    environment {
        DOCKER_REPO = "flight-backend"
    }
    stages {
        stage('Code-checkout'){
            steps{
                git branch: 'main',
                    url: 'https://github.com/mayurmwagh/flight-reservation-backend.git'
            }
        }
        stage('Code-build'){
            steps{
                sh 'mvn clean package'
            }
        }
        stage ('Docker-build'){
            steps {
                sh 'docker build -t ${DOCKER_REPO}:${BUILD_NUMBER} . '
            }
        }
        stage ('Docker-login'){
            steps{
                withCredentials([
                            usernamePassword(
                                credentialsId: 'docker-hub-creds',
                                usernameVariable: 'DOCKER_USERNAME',
                                passwordVariable: 'DOCKER_PASSWORD'
                            )
                        ]) 
                        {    
                           sh 'docker login -u ${DOCKER_USERNAME} -p ${DOCKER_PASSWORD}'
                    }
            }
        }
    }
}