pipeline {
    agent any

    tools {
        maven 'Maven_3.8.6'   // Name of Maven installation configured in Jenkins
        jdk 'JDK17'           // Name of JDK installation configured in Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/your-org/your-repo.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package'
            }
            post {
                success {
                    archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
                }
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t my-springboot-app .'
            }
        }

        stage('Deploy') {
            steps {
                sh 'docker run -d -p 8080:8080 --name springboot-app my-springboot-app'
            }
        }
    }

    post {
        always {
            junit 'target/surefire-reports/*.xml'
        }
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed. Check logs.'
        }
    }
}