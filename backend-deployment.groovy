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
            agent {
                docker {
                    image 'maven:3.9.11-eclipse-temurin-21'
                    reuseNode true
                }
            }

            steps {
                sh '''
                  mvn clean package 

                '''
            }
        }
    }
}