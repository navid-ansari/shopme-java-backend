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
    }
}