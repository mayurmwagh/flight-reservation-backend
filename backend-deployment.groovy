pipeline {
    agent any

    stages {

        stage('code-checkout') {
            agent any
            steps {
                git branch: 'main',
                    url: 'https://github.com/mayurmwagh/flight-reservation-backend.git'
            }
        }

        stage('build-stage') {

            steps {
                sh '''
                  mvn clean package 

                '''
            }
        }
    }
}