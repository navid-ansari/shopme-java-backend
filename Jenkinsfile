pipeline {
    agent any
    tools {
       maven 'Maven_Home'
    }
    stages{
        stage('Build Maven'){
            steps{
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/navid-ansari/shopme-java-backend']]])
                sh 'mvn clean install'
            }
        }
    }
}