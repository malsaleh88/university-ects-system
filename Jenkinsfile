pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/malsaleh88/university-ects-system.git'
            }
        }

        stage('Build & Run with Docker Compose') {
            steps {
                bat 'docker-compose up --build -d'
            }
        }
    }

    post {
        always {
            echo 'Cleaning up containers...'
            bat 'docker-compose down'
        }
    }
}
