pipeline {
    agent any
    tools {
        terraform 'tf1.6'
    }
    stages {
        stage('Clone Git repo') {
            steps {
                git(
                    branch: 'main', 
                    url: 'https://github.com/glass91/k8S_AWS_infra.git', 
                    credentialsId: 'acces_to_git'
                )
            }
        } 
         stage('Terraform Plan') {
            steps {
                sh '''
                cd ./TF
                echo "yes" | terraform init
                terraform plan -out=terraform.tfplan 
                '''
            }
        }

        stage('Plan verification and user input') {
            steps {
                input(
                    message: 'proceed or abort?', 
                    ok: 'ok'
                )
            }
        }

        stage('Terraform Apply') {
            steps {
                sh '''
                cd ./TF
                terraform apply terraform.tfplan
                '''
            }
        }
    }
}        