import hudson.model.*

pipeline {
    agent any
    tools {
        maven "Maven"
    }
    environment {
        NEXUS_VERSION = "nexus3"
        NEXUS_PROTOCOL = "http"
        NEXUS_URL = "http://192.168.1.149:8081"
        NEXUS_REPOSITORY = "maven-nexus-repo"
        NEXUS_CREDENTIAL_ID = "cartas123456"
        REPOSITORY = "https://github.com/DivanioSilva/spring-boot-graphql.git"
    }
    stages {
        stage('get_commit_details') {
                steps {
                    script {
                        env.GIT_COMMIT_MSG = sh (script: 'git log -1 --pretty=%B ${GIT_COMMIT}', returnStdout: true).trim()
                        env.GIT_AUTHOR = sh (script: 'git log -1 --pretty=%cn ${GIT_COMMIT}', returnStdout: true).trim()
                    }
                }
            }
        stage("Maven Build") {
            steps {
                script {
                    sh "mvn package -DskipTests=true"
                }
            }
        }
        stage("Publish to Nexus Repository Manager") {
            steps {
                script {
                    // Read POM xml file using 'readMavenPom' step , this step 'readMavenPom' is included in: https://plugins.jenkins.io/pipeline-utility-steps
                    def pom = readMavenPom file: "pom.xml";
                    // Find built artifact under target folder
                    echo 'Pom info::::'
                    echo 'pom.packaging: ' +pom.packaging
                    echo 'pom.groupId: ' +pom.groupId
                    echo 'pom.name: ' +pom.name
                    def nexusRepoName = pom.version.endsWith("SNAPSHOT") ? "maven-nexus-repo-snapshots" : "maven-nexus-repo"
                    filesByGlob = findFiles(glob: "target/*.${pom.packaging}");
                    echo 'filesByGlob' +filesByGlob
                    // Print some info from the artifact found
                    echo "${filesByGlob[0].name} ${filesByGlob[0].path} ${filesByGlob[0].directory} ${filesByGlob[0].length} ${filesByGlob[0].lastModified}"
                    // Extract the path from the File found
                    artifactPath = filesByGlob[0].path;
                    echo 'artifactPath: '+ artifactPath
                    // Assign to a boolean response verifying If the artifact name exists
                    artifactExists = fileExists artifactPath;
                    echo 'artifactExists:' +artifactExists
                    if(artifactExists) {
                        echo "*** File: ${artifactPath}, group: ${pom.groupId}, packaging: ${pom.packaging}, version ${pom.version}";

                        nexusArtifactUploader artifacts: [
                            [
                                artifactId: pom.name,
                                classifier: '',
                                file: artifactPath,
                                type: pom.packaging]
                            ],
                            credentialsId: 'nexus3',
                            groupId: pom.groupId,
                            nexusUrl: '192.168.1.149:8081',
                            nexusVersion: NEXUS_VERSION,
                            protocol: NEXUS_PROTOCOL,
                            repository: nexusRepoName,
                            version: pom.version
                    } else {
                        error "*** File: ${artifactPath}, could not be found";
                    }
                }
            }
        }
    }
}
