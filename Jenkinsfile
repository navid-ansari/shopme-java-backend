pipeline {
    agent any
    tools {
        maven 'Maven_Home'
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
                    withCredentials([string(credentialsId: 'navidansari', variable: 'dockerhubpwd')]) {
                        echo 'password is ${dockerhubpwd}'
                        if ('${dockerhubpwd}' == 'navidansari') {
                            echo 'password is navidansari'
                        } else {
                            echo 'password is not navidansari'
                        }
                        bat 'docker login -u navidansari -p ${dockerhubpwd}'
                    }
                    bat 'docker push navidansari/shopme-java-backend'
                }
            }
         }

    }
}