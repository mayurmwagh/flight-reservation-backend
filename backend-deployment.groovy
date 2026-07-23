pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/mayurmwagh/flight-reservation-backend.git'
            }
        }

        stage('Verify Java') {
            steps {
                sh '''
                whoami
                echo "JAVA_HOME=$JAVA_HOME"
                which java
                java -version
                which javac
                javac -version
                mvn -version
                '''
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
    }
}