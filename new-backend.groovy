pipeline {
    agent any
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
    }
}