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
                        image 'maven:3.9.11-amazoncorretto-21'
                        
                        reuseNode true
                        }
                    }
                sh ''' 
                     java -version
                     javac -version
                     mvn -version
                     mvn clean package
                '''
            }
        }
    }

}