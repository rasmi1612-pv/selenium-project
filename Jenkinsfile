pipeline {

    agent any

    tools {
        jdk 'JDK17'
        maven 'Maven3'
    }

    stages {

        stage('Checkout') {
            steps {
                git 'https://github.com/rasmi1612-pv/selenium-project.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Run Tests') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Archive Reports') {
            steps {
                archiveArtifacts artifacts: 'Reports/**/*'
            }
        }

    }

    post {

        always {
            junit '**/surefire-reports/*.xml'
        }

    }
}
