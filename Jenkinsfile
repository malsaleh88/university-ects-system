pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/malsaleh88/university-ects-system.git'
            }
        }

        stage('Clean old containers') {
            steps {
                bat 'docker-compose down || echo "No containers to remove"'
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
