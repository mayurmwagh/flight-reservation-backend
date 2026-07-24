pipeline {
    agent any
    envrionment {
        DOCEKR_REPO = "flight-backend"
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
    }
}