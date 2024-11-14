// vars/checkoutRepo.groovy
def call1(String repoUrl, String branch = 'main') {
    checkout([
        $class: 'GitSCM', 
        branches: [[name: "*/${branch}"]],
        userRemoteConfigs: [[url: repoUrl]]
    ])
}


// vars/sendEmail.groovy
def call2(String subject, String body, String recipient) {
    emailext(
        subject: subject,
        body: body,
        to: recipient
    )
}


// vars/buildAndTest.groovy
def call3() {
    stage('Build') {
        sh 'mvn clean install'
    }
    stage('Test') {
        sh 'mvn test'
    }
}


// vars/deployToServer.groovy
def call4(String server, String artifactPath) {
    sshagent(credentials: ['your-ssh-credential-id']) {
        sh """
        scp ${artifactPath} user@${server}:/path/to/deploy
        ssh user@${server} 'sudo systemctl restart your-service'
        """
    }
}


// vars/archiveArtifacts.groovy
def call5(String artifactsPath) {
    archiveArtifacts artifacts: artifactsPath
}


def call6(String greet){
     sh "
     echo "Hello ${greet} welcome to jenkins - SHRD lib"
     "
}
