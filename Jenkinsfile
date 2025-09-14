pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/malsaleh88/university-ects-system.git'
            }
        }

        stage('Build Backend') {
            steps {
                bat 'docker build -t ects-backend ./university-ects-system'
            }
        }

        stage('Build Frontend') {
            steps {
                bat 'docker build -t ects-frontend ./frontend'
            }
        }
    }
}
