pipeline {
    agent any
    environment {
        DOCKER_REPO = "flight-reservation53"
        DOCKER_USER = "mayurwagh"
        

    }

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
        stage('Build Docker Image') {
            steps {
                sh '''
                docker build -t ${DOCKER_REPO}:${BUILD_NUMBER} .
                ''' 
            }
        }
        stage('Docker login'){
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

        stage('Docker push'){
            steps{
               withCredentials([
                        usernamePassword(
                            credentialsId: 'docker-hub-creds',
                            usernameVariable: 'DOCKER_USERNAME',
                            passwordVariable: 'DOCKER_PASSWORD'
                        )
                    ]) 
                    {
                        sh '''
                            docker tag ${DOCKER_REPO}:${BUILD_NUMBER} \
                            ${DOCKER_REPO}/${DOCKER_USER}:${BUILD_NUMBER}

                            docker push ${DOCKER_REPO}/${DOCKER_USER}:${BUILD_NUMBER}
                        '''
                    }
                }
            }
    }
}