pipeline {
    agent any
    tools {
        maven 'Maven_Home'
    }
    environment {
        DOCKERHUB_CREDENTIALS=credentials('dockerhub')
    }
    stages{
         stage('Checkout'){
             steps{
                 checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/navid-ansari/shopme-java-backend']]])
             }
         }
         stage('Build'){
             steps{
                 bat 'mvn clean install'
             }
         }
         stage('Junit'){
             steps{
                 bat 'mvn -B test'
             }
         }
         stage('Build Docker Image'){
             steps{
                 script{
                     bat 'docker build -t navidansari/shopme-java-backend .'
                 }
             }
         }
         stage('Push Image to Dockerhub'){
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'PASSWORD', usernameVariable: 'USERNAME')]) {
                    echo USERNAME
                    echo PASSWORD
                    bat 'docker login -u navidansari -p navidansari1'
                }
                withCredentials([string(credentialsId: 'DOCKER_USERNAME', variable: 'DOCKER_USERNAME'), string(credentialsId: 'DOCKER_PASSWORD', variable: 'DOCKER_PASSWORD')]) {
                    echo DOCKER_USERNAME
                    echo DOCKER_PASSWORD
                }
                bat 'docker login -u ${DOCKERHUB_CREDENTIALS_USR} -p navidansari1'
            }
         }
    }
}