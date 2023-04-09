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
            steps{
                script{
                    if ('$DOCKERHUB_CREDENTIALS_PSW' == 'dckr_pat_pIiRDHkkYL0xhoCXIbmcl2GC17c') {
                        echo 'Token matched'
                    } else {
                        echo 'Token not matched'
                    }
                     bat 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
                }
            }
         }
    }
}