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
            junit 'target/surefire-reports/*.xml'

            publishHTML(target: [
                reportDir: 'Reports',
                reportFiles: 'SauceDemoReport.html',
                reportName: 'Extent Report',
                keepAll: true,
                alwaysLinkToLastBuild: true,
                allowMissing: false
            ])
    }
}
}
