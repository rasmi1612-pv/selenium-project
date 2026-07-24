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
        publishHTML(target: [
            reportDir: 'test-output',
            reportFiles: 'index.html',
            reportName: 'TestNG Report',
            keepAll: true,
            alwaysLinkToLastBuild: true
        ])
    }
}
}
