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
                echo "Target directory очищен и тесты скомпилированы"
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
                            catchError(buildResult: 'UNSTABLE', stageResult: 'FAILURE') {
                                bat "mvn test -Dbrowser=firefox -Dbrowser.version=${BROWSER_VERSION} -Dselenoid.url=${SELENOID_URL}"
                            }
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
            sendEmailNotification("SUCCESS")
        }

        unstable {
            sendEmailNotification("UNSTABLE: Tests Failed")
        }

        failure {
            sendEmailNotification("FAILURE: Build Error")
        }

        aborted {
            echo "Сборка была прервана пользователем"
        }
    }
}

def sendEmailNotification(String status) {
    emailext (
        subject: "${status} | ${PROJECT_NAME} | Build #${env.BUILD_NUMBER}",
        body: """
            <html>
            <body>
                    <a href="${env.BUILD_URL}allure/">Allure Report</a>
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