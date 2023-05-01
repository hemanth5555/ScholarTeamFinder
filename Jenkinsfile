// pipeline {
//   agent any
//   stages {
//     stage('Deploy') {
//       steps {
//         sh 'docker-compose up --build'
//       }
//     }
//   }
// }
pipeline {
  agent any
  stages {
    stage('Deploy') {
      steps {
        script {
          dockerCompose(build: true, file: 'docker-compose.yml', projectName: 'scholar-team-finder', upArgs: '--build')
        }
      }
    }
  }
}
