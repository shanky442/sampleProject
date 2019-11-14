node {

    withMaven(maven:'maven') {

        stage('Checkout') {
            git branch: 'master',
                credentialsId: '12345-1234-4696-af25-123455',
                url: 'https://github.com/shanky442/sampleProject.git'
        }

        stage('Build') {
            sh 'mvn clean install'

            def pom = readMavenPom file:'pom.xml'
            print pom.version
            env.version = pom.version
        }

        stage('Image') {
            dir ('sampleProject') {
                def app = docker.build "sampleProject:${env.version}"
                app.push()
            }
        }

        stage ('Run') {
            docker.image("sampleProject:${env.version}").run('-p 2222:2222 -t sampleProject:${env.version}')
        }

        stage ('Final') {
            //send email notification
        }

    }

}