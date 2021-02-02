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
                sh 'mvn -B -DskipTests -f <./Backend/> clean package'
            }
        }
        stage('Docker build') {
            agent any
            steps {
                sh 'docker build -t <front-image-name>:latest <./Frontend/moa/>'
                sh 'docker build -t <back-image-name>:latest <./Backend/>'
            }
        }
        stage('Docker run') {
            agent any
            steps {
                sh 'docker ps -f name=<front-image-name> -q \
                  | xargs --no-run-if-empty docker container stop'
                sh 'docker ps -f name=<back-image-name> -q \
                  | xargs --no-run-if-empty docker container stop'
                sh 'docker container ls -a -f name=<front-image-name> -q \
                  | xargs -r docker container rm'
                sh 'docker container ls -a -f name=<back-image-name> -q \
                  | xargs -r docker container rm'
                sh 'docker images -f dangling=true && \
                    docker rmi $(docker images -f "dangling=true" -q)'
                sh 'docker run -d --name <front-image-name> -p 80:80 <front-image-name>:latest'
                sh 'docker run -d --name <back-image-name> -p 8080:8080 <back-image-name>:latest'
            }
        }
    }
}