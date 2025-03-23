pipeline {
    agent {
        label 'sanket'
    }

    environment {
        DOCKER_IMAGE = 'sanketnalage/frontend'
        DOCKER_TAG = 'latest'
        KUBE_DEPLOYMENT_FILE = 'frontend-deployment.yml'
    }

    stages {
        stage('Clone Repository') {
            steps {
                deleteDir() // Clean the workspace
                git branch: 'backend', url: 'https://github.com/tejasgund02/Todo-List-Project.git'
                echo 'Clone successful'
            }
        }

        // stage('Build Docker Image') {
        //     steps {
        //         sh 'pwd' // Print current working directory
        //         sh "docker build --no-cache -t ${DOCKER_IMAGE}:${DOCKER_TAG} ."
        //         echo 'Docker build successful'
        //     }
        // }

        // stage('Push Docker Image') {
        //     steps {
        //         sh "docker tag ${DOCKER_IMAGE}:${DOCKER_TAG} ${DOCKER_IMAGE}:${DOCKER_TAG}"
        //         sh "docker push ${DOCKER_IMAGE}:${DOCKER_TAG}"
        //         echo 'Docker push successful'
        //     }
        // }

        // stage('Deploy to Kubernetes') {
        //     steps {
        //         sh """
        //             kubectl apply -f ${KUBE_DEPLOYMENT_FILE}
        //             kubectl rollout restart deployment frontend
        //         """
        //         echo 'Kubernetes deployment successful'
        //     }
        // }
    }

    post {
        success {
            echo 'Pipeline executed successfully!'
        }
        failure {
            echo 'Pipeline failed!'
        }
        always {
            echo 'Pipeline execution completed.'
        }
    }
}