pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/malsaleh88/university-ects-system.git'
            }
        }

        stage('Build Backend') {
            steps {
                sh 'docker build -t ects-backend ./university-ects-system'
            }
        }

        stage('Build Frontend') {
            steps {
                sh 'docker build -t ects-frontend ./frontend'
            }
        }
    }
}
