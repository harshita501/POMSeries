pipeline{
    
    agent any
    stages{
        stage("Build"){
            steps{
            echo("Building")
            }
        }
    }
    
    stages{
        stage("Deploy on Dev"){
            steps{
            echo("deploy on dev")
            }
        }
    }
    
    stages{
        stage("Deploy on QA"){
            steps{
            echo("deploy on qa")
            }
        }
    }
    
    stages{
        stage("Regression test"){
            steps{
            echo("running regression test")
            }
        }
    }
    
    stages{
        stage("deploy on stage"){
            steps{
            echo("deploy on stage")
            }
        }
    }
    
    stages{
        stage("Sanity test on stage"){
            steps{
            echo("Sanity test on stage")
            }
        }
    }
    
    stages{
        stage("Deploy on PROD"){
            steps{
            echo("deploy on PROD")
            }
        }
    }
   
}