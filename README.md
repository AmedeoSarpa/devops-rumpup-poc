# devops-rumpup-poc
Repository to start to "play" with some DevOps technologies and tools like k8s, docker and helm.

## Hot to build in local
0) You need to have docker engine, helm and k8s installed on your local machine
1) Build docker image with 
```docker 
docker build -f quarkus-kafka-producer/Dockerfile.jvm -t <image-name> .
```
2) Make sure you have a k8s cluster running (in my case, I use docker-desktop cluster)
3) Go inside the *devops-files/devops-rumpup-poc-helm-charts/charts/quarkus-kafka-producer-helm-chart/values.yaml* and set the <image-name> under the path deployment>containers>name
4) Go inside the *devops-files/devops-rumpup-poc-helm-charts* folder and deploy the application in the cluster using the following helm command
```
helm install <application-name> .
```
Note that this command will install also a nginx-ingress controller, please make sure that all pods are up and running!