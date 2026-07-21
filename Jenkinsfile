pipeline {

    agent any

    tools {
        jdk 'JDK17'
        maven 'Maven'
    }

    stages {

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
                archiveArtifacts artifacts: 'Reports/**/*', allowEmptyArchive: true
            }
        }
    }

    post {
        always {
            junit testResults: '**/surefire-reports/*.xml',
                  allowEmptyResults: true
        }
    }
}
