pipeline {
    agent any
    environment {
        DOCKERHUB_CREDENTIALS = credentials('DockerHub-Credentials')
        SONARQUBE_CREDENTIALS = credentials('SonarQube-Credentials')
    }
    stages {
        stage('Checkout GIT') {
            steps {
                echo 'Pulling ...';
                checkout([$class: 'GitSCM', branches: [[name: '*/master']],
                userRemoteConfigs: [[url: 'https://github.com/5ARCTIC5-G3-DevOps/5ARCTIC5-G3-DevOps.git',credentialsId: 'GitHub-Credentials']]])
            }
        }
        stage('Clean & Build') {
            steps {
                echo 'Cleaning the project ...';
                sh 'mvn clean && mvn package';
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test';
            }
        }
        stage('MVN SONARQUBE'){
            steps {
                sh 'mvn sonar:sonar -Dsonar.login=$SONARQUBE_CREDENTIALS'
            }
        }
        stage('Depot : NEXUS') {
            steps {
                sh 'mvn deploy -DskipTests'
            }
        }
        stage('Pull from NEXUS & Build Image') {
            steps {
                script{
                    sh 'curl -o 5ARCTIC5-G3-DevOps-latest.jar "http://192.168.187.157:8081/repository/maven-releases/tn/esprit/5ARCTIC5-G3-DevOps/latest/5ARCTIC5-G3-DevOps-latest.jar"'
                    dir('/var/lib/jenkins/workspace/validation'){
                    sh 'docker build -t $DOCKERHUB_CREDENTIALS_USR/******:EmnaHANNACHI-5ARCTIC5-G3-DevOps . '
                }
                }
            }
        }
        stage('Login & Push Image To HUB'){
            steps {
                script{
                    sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
                    sh 'docker push $DOCKERHUB_CREDENTIALS_USR/*****:EmnaHANNACHI-5ARCTIC5-G3-DevOps'

                }
            }
        }
        stage('Docker-Compose') {
            steps {
                script {
                    sh 'sudo cp /root/SpringAndAngular/docker-compose.yml /var/lib/jenkins/workspace/validation/'
                    sh 'docker-compose up -d'
                }
            }
        }
    }
    post {
        success {
                emailext body: 'La construction a réussi.',
                subject: 'Succès',
                to: 'emna.hannachi@esprit.tn',
                mimeType: 'text/plain'
        }
        failure {
                emailext body: 'La construction a échoué. Veuillez vérifier le build sur Jenkins.',
                subject: 'Échec du build Jenkins',
                to: 'emna.hannachi@esprit.tn',
                mimeType: 'text/plain',
                attachLog: true
        }
    }
}