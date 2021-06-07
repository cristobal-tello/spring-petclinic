{
    "jenkins-runner.hostConfigs": {
        "host-with-password": {
            "url": "http://localhost:8080",
            "user": "admin",
            "password": "admin",
            "useCrumbIssuer": false,
            "rejectUnauthorizedCert": false
        },
        "host-prompt-for-password": {
            "url": "http://server:8090",
            "user": "admin"
        },
        "host-no-password": {
            "url": "http://server-no-auth:8090"
        },
        "spc-pipeline": {
            "url": "http://localhost:8080",
            "user":"<userhere>",
            "password": "<passwordhere>",
            "useCrumbIssuer":true
        }

    },
    "jenkins-runner.jobs": {
        "spc": {
            "isDefault": true,
            "runWith": "spc-pipeline",
            "name": "spc"
          
        },

        "test-1": {
            "isDefault": true,
            "runWith": "host-no-password",
            "name": "pipeline-test",
            "parameters": {
                "first": 1,
                "second": "2nd"
            },
            "environment": {
                "HOST_NAME": "localhost"
            }
        },
        "test-2": {
            "runWith": [
                "host-with-password",
                "host-prompt-for-password"
            ],
            "name": "pipeline-test"
        }
    }
}