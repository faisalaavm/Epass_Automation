pipeline {
    agent any

    stages {
        stage('Run Tests') {
            steps {
                bat 'mvn clean test'
            }
        }
    }

    post {
        always {
            junit 'target/surefire-reports/*.xml'
             publishHTML([
            reportDir: 'target/surefire-reports',
            reportFiles: 'index.html',
            reportName: 'TestNG Report'
        ])
        }
    }
}

