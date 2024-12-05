pipeline {
    agent any
    tools {
        maven 'maven3'
    }
    stages {
    stage('Verify Tools') {
        steps {
            bat 'mvn -version'
            bat 'docker --version'
        }
    }
        stage('Checkout Code') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [],
                          userRemoteConfigs: [[url: 'https://github.com/ravalireddy13/springAppJenkins']]])
            }
        }
        stage('Build with Maven') {
            steps {
                bat 'mvn clean install'
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    bat 'docker build -t ravalireddy13/users-backend .'
                }
            }
        }
    }
}



