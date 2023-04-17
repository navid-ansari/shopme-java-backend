pipeline {
    agent any
    tools {
        maven 'Maven_Home'
    }
    environment {
    //    DOCKERHUB_CREDENTIALS=credentials('dockerhub')
        DOCKERHUB_CREDENTIALS=credentials('dockerhubnew')
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
            echo DOCKERHUB_CREDENTIALS_USR
                bat 'docker login -u %DOCKERHUB_CREDENTIALS_USR% -p %DOCKERHUB_CREDENTIALS_PSW%'
                bat 'echo %DOCKERHUB_CREDENTIALS_PSW% | docker login -u %DOCKERHUB_CREDENTIALS_USR% --password-stdin'
            }
         }
    }
}