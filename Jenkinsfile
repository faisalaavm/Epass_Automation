pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git 'git@github.com:faisalaavm/Epass_Automation.git'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn clean test'
            }
        }
    }
}
