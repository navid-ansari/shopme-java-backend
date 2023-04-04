pipeline {
    agent any
    tools {
       maven 'Maven_Home'
    }
    stages{
        stage('Junit'){
            steps{
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/navid-ansari/shopme-java-backend']]])
                sh 'mvn -B test'
            }
        }
        stage('Build'){
            steps{
                sh 'mvn clean install'
            }
        }
    }
}