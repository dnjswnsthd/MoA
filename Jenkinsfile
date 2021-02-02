pipeline {
    agent none
    options { skipDefaultCheckout(true) }
    stages {
        stage('Build and Test') {
            agent {
                docker {
                    image 'maven:3-alpine'
                    args '-v /root/.m2:/root/.m2'
                }
            }
            options {skipDefaultCheckout(false)}
            steps {
                sh 'mvn -B -DskipTests -f ./Backend/pom.xml clean package'
            }
        }
        stage('Docker build') {
            agent any
            steps {
                sh 'docker build -t MoaFront:latest ./Frontend/moa/Dockerfile'
                sh 'docker build -t MoaBack:latest ./Backend/Dockerfile'
            }
        }
        stage('Docker run') {
            agent any
            steps {
                sh 'docker ps -f name=MoaFront -q \
                  | xargs --no-run-if-empty docker container stop'
                sh 'docker ps -f name=MoaBack -q \
                  | xargs --no-run-if-empty docker container stop'
                sh 'docker container ls -a -f name=MoaFront -q \
                  | xargs -r docker container rm'
                sh 'docker container ls -a -f name=MoaBack -q \
                  | xargs -r docker container rm'
                sh 'docker images -f dangling=true && \
                    docker rmi $(docker images -f "dangling=true" -q)'
                sh 'docker run -d --name MoaFront -p 80:80 MoaFront:latest'
                sh 'docker run -d --name MoaBack -p 8080:8080 MoaBack:latest'
            }
        }
    }
}