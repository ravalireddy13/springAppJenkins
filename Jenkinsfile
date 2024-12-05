pipeline {
    agent any
    tools {
        maven 'maven3'
    }
    stages {
        stage('Checkout Code') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [],
                          userRemoteConfigs: [[url: 'https://github.com/ravalireddy13/springAppJenkins']]])
            }
        }
        stage('Build with Maven') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    sh 'docker build -t ravalireddy13/users-backend .'
                }
            }
        }
    }
}



