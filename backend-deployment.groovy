pipeline {
    agent any 
    stages {
        stage('code-checkot'){
            steps{
                git branch: 'main',
                    url: 'https://github.com/mayurmwagh/flight-reservation-backend.git'
            }
        }
        stage('build-stage'){
            steps{
                agent {
                    docker {
                        image 'maven:3.9.9-eclipse-temurin-21'
                        reuseNode true
                        }
                    }
                sh 'mvn clean package'
            }
        }
    }

}