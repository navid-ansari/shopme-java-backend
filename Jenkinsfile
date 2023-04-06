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
        stage('Build docker image'){
            steps{
                script{
                    bat 'docker build -t navidansari/shopme-java-backend .'
                }
            }
        }
        stage('Push image to Dockerhub'){
            steps{
                script{
                   withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhubpwd')]) {
                        bat 'docker login -u navidansari -p ${dockerhubpwd}'
                   }
                   bat 'docker push navidansari/shopme-java-backend'
                }
            }
        }
    }
}