pipeline {
    agent any

    triggers {
        cron('H 2 * * *')
    }

    tools {
        maven 'Maven-3.9.6'
        jdk 'JDK-17'
    }

    // Переменные
    environment {
        ALLURE_RESULTS_DIR = 'target/allure-results'
        ALLURE_REPORT_DIR = 'target/site/allure-maven-plugin'
        PROJECT_NAME = 'registry_office'
        EMAIL_RECIPIENT = 'owlwhitewing@gmail.com'
    }

    options {
        disableConcurrentBuilds()
        buildDiscarder(logRotator(numToKeepStr: '10'))
        timestamps()
    }

    stages {
        stage('Clone from GitHub') {
            steps {
                git branch: 'master',
                    url: 'https://github.com/owwwlwwwhite/registry_office.git',
                    credentialsId: 'github-credentials'
                echo "Код получен из GitHub. Коммит: ${env.GIT_COMMIT?.take(7)}"
            }
        }

        stage('Install Dependencies') {
            steps {
                sh 'mvn dependency:resolve -q'
                echo "✅ Зависимости готовы"
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    try {
                        sh '''
                            mvn clean test \
                                -Dsurefire.useFile=false \
                                -Dallure.results.directory=${ALLURE_RESULTS_DIR}
                        '''
                        echo "✅ Тесты выполнены"
                    } catch (Exception e) {
                        currentBuild.result = 'UNSTABLE'
                        echo "⚠Тесты завершились с ошибками: ${e.getMessage()}"
                    }
                }
            }
            post {
                always {
                    junit allowEmptyResults: true,
                          testResults: 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Generate Allure Report') {
            steps {
                allure([
                    includeProperties: false,
                    jdk: '',
                    properties: [],
                    reportBuildPolicy: 'ALWAYS',
                    results: [[path: "${ALLURE_RESULTS_DIR}"]]
                ])
                echo "Allure отчёт сгенерирован"
            }
        }

        stage('Archive Artifacts') {
            steps {
                archiveArtifacts artifacts: '''
                    ${ALLURE_RESULTS_DIR}/**,
                    target/surefire-reports/**,
                    target/logs/**,
                    target/screenshots/**
                ''',
                allowEmptyArchive: true,
                fingerprint: true
                echo "Артефакты сохранены"
            }
        }
    }

    post {
        always {
            cleanWs(cleanWs: [
                deleteDirs: true,
                disableDeferredWipeout: true,
                notFailBuild: true
            ])
            echo "Workspace очищен"
        }

        success {
            sendEmailNotification("SUCCESS")
        }

        unstable {
            sendEmailNotification("⚠UNSTABLE: Tests Failed")
        }

        failure {
            sendEmailNotification("FAILURE: Build Error")
        }

        aborted {
            echo "Сборка была прервана пользователем"
        }
    }
}

def sendEmailNotification(String status, String color) {
    emailext (
        subject: "${status} | ${PROJECT_NAME} | Build #${env.BUILD_NUMBER}",
        body: """
            <html>
            <body style="font-family: Arial, sans-serif; line-height: 1.6; color: #333;">

                <h2 style="color: ${color}; border-bottom: 2px solid ${color}; padding-bottom: 10px;">
                    ${status}
                </h2>

                <table style="border-collapse: collapse; width: 100%; max-width: 600px; margin: 20px 0;">
                    <tr style="background-color: #f8f9fa;">
                        <td style="padding: 10px; border: 1px solid #ddd; font-weight: bold;">Project</td>
                        <td style="padding: 10px; border: 1px solid #ddd;">${PROJECT_NAME}</td>
                    </tr>
                    <tr>
                        <td style="padding: 10px; border: 1px solid #ddd; font-weight: bold;">Build Number</td>
                        <td style="padding: 10px; border: 1px solid #ddd;">#${env.BUILD_NUMBER}</td>
                    </tr>
                    <tr style="background-color: #f8f9fa;">
                        <td style="padding: 10px; border: 1px solid #ddd; font-weight: bold;">Status</td>
                        <td style="padding: 10px; border: 1px solid #ddd; color: ${color}; font-weight: bold;">${status}</td>
                    </tr>
                    <tr>
                        <td style="padding: 10px; border: 1px solid #ddd; font-weight: bold;">Started</td>
                        <td style="padding: 10px; border: 1px solid #ddd;">${env.BUILD_TIMESTAMP}</td>
                    </tr>
                    <tr style="background-color: #f8f9fa;">
                        <td style="padding: 10px; border: 1px solid #ddd; font-weight: bold;">Duration</td>
                        <td style="padding: 10px; border: 1px solid #ddd;">${currentBuild.durationString}</td>
                    </tr>
                    <tr>
                        <td style="padding: 10px; border: 1px solid #ddd; font-weight: bold;">Commit</td>
                        <td style="padding: 10px; border: 1px solid #ddd;">
                            <a href="https://github.com/your-username/your-repo/commit/${env.GIT_COMMIT}">
                                ${env.GIT_COMMIT?.take(7)}
                            </a>
                        </td>
                    </tr>
                </table>

                <h3>Quick Links:</h3>
                <ul>
                    <li><a href="${env.BUILD_URL}">Build Details</a></li>
                    <li><a href="${env.BUILD_URL}allure/">Allure Report</a></li>
                    <li><a href="${env.BUILD_URL}console">Console Output</a></li>
                    <li><a href="https://github.com/your-username/your-repo">GitHub Repository</a></li>
                </ul>

                <div style="margin-top: 30px; padding: 15px; background-color: #e7f3ff; border-left: 4px solid #007bff; border-radius: 4px;">
                    <strong>Note:</strong><br>
                    Allure report is available via "Allure Report" link in the left menu of this build.<br>
                    If you don't see it, check that the 'Allure Jenkins Plugin' is installed.
                </div>

                <hr style="margin: 30px 0; border: none; border-top: 1px solid #eee;">
                <p style="font-size: 12px; color: #666;">
                    This email was sent by Jenkins CI at ${new Date().format('yyyy-MM-dd HH:mm:ss')}<br>
                    Build URL: <a href="${env.BUILD_URL}">${env.BUILD_URL}</a>
                </p>

            </body>
            </html>
        """,
        to: "${EMAIL_RECIPIENT}",
        from: 'jenkins-ci@localhost',
        replyTo: 'jenkins-ci@localhost',
        mimeType: 'text/html',
        attachLog: true,
        compressLog: true
    )
}