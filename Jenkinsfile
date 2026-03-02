pipeline {
    agent any

    triggers {
        cron('H 2 * * *')
    }

    tools {
        maven 'Maven_3.9.11'
        jdk 'java_21'
    }

    environment {
        ALLURE_RESULTS_DIR = 'target/allure-results'
        ALLURE_REPORT_DIR = 'target/site/allure-maven-plugin'
        PROJECT_NAME = 'registry_office'
        EMAIL_RECIPIENT = 'owlwhitewing@gmail.com'

        SELENOID_URL = 'http://localhost:4444/wd/hub'
        BROWSERS = 'chrome,firefox,MicrosoftEdge'
        BROWSER_VERSION = '120.0'
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

        stage('Prepare Secrets') {
                    steps {
                        script {
                            withCredentials([file(credentialsId: 'app-secrets-file', variable: 'SECRETS_FILE_PATH')]) {
                                bat 'copy /Y "%SECRETS_FILE_PATH%" ".\\src\\test\\resources\\test_auth.properties"'
                                echo "Секретный файл подключен"
                            }
                        }
                    }
                }

        stage('Install Dependencies') {
            steps {
                bat 'mvn dependency:resolve'
                echo "Зависимости готовы"
            }
        }

        stage('Clean & Test-compile Target Directory') {
            steps {
                bat 'mvn clean'
                bat 'mvn test-compile'
                echo "Target directory очищен"
            }
        }

        stage('Start Selenoid') {
            steps {
                script {
                    echo "Запуск Selenoid..."
                    bat 'docker rm -f selenoid || echo "Selenoid container not found, continuing..."'
                    bat '''
                        docker run -d ^
                          --name selenoid ^
                          -p 4444:4444 ^
                          -e DOCKER_HOST=tcp://host.docker.internal:2375 ^
                          -e DOCKER_API_VERSION=1.44 ^
                          -v C:/Users/Sovushko/.aerokube/selenoid:/etc/selenoid:ro ^
                          aerokube/selenoid:latest-release ^
                          -conf /etc/selenoid/browsers.json
                    '''
                    bat 'powershell -Command "Start-Sleep -Seconds 10"'
                    echo "Selenoid запущен и готов"
                }
            }
        }

        stage('Parallel Tests - 3 Browsers') {
            parallel {
                stage('Chrome') {
                    steps {
                        script {
                            echo "Запуск тестов на Chrome..."
                            bat "mvn test -Dbrowser=chrome -Dbrowser.version=${BROWSER_VERSION} -Dselenoid.url=${SELENOID_URL}"
                        }
                    }
                }
                stage('Firefox') {
                    steps {
                        script {
                            echo "Запуск тестов на Firefox..."
                            bat "mvn test -Dbrowser=firefox -Dbrowser.version=${BROWSER_VERSION} -Dselenoid.url=${SELENOID_URL}"
                        }
                    }
                }
                stage('Edge') {
                    steps {
                        script {
                            echo "Запуск тестов на Edge..."
                            bat "mvn test -Dbrowser=MicrosoftEdge -Dbrowser.version=${BROWSER_VERSION} -Dselenoid.url=${SELENOID_URL}"
                        }
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
                script {
                    def resultsPath = "${WORKSPACE}\\${ALLURE_RESULTS_DIR}"
                    echo "Looking for Allure results in: ${resultsPath}"
                    if (fileExists(resultsPath)) {
                        bat "dir \"${resultsPath}\""
                        echo "Allure results found"
                    } else {
                        echo "Allure results NOT found at: ${resultsPath}"
                        bat "dir target"
                    }
                }
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
            script {
                echo "Остановка Selenoid..."
                    bat 'docker stop selenoid || echo "Selenoid not running"'
                    bat 'docker rm -f selenoid || echo "Selenoid container not found"'
            }
            cleanWs(
                deleteDirs: true,
                disableDeferredWipeout: true,
                notFailBuild: true
            )
            echo "Workspace очищен"
        }

        success {
            sendEmailNotification("SUCCESS", "#28a745")
        }

        unstable {
            sendEmailNotification("UNSTABLE: Tests Failed", "#ffc107")
        }

        failure {
            sendEmailNotification("FAILURE: Build Error", "#dc3545")
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
                            <a href="https://github.com/owwwlwwwhite/registry_office/commit/${env.GIT_COMMIT}">
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
                    <li><a href="https://github.com/owwwlwwwhite/registry_office/">GitHub Repository</a></li>
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