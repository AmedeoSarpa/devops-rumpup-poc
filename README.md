# devops-rumpup-poc
Repository to start to "play" with some DevOps technologies and tools like k8s, docker and helm.

## Hot to build in local
0) You need to have docker engine, helm and k8s installed on your local machine
1) Build docker image with 
```docker 
docker build -f quarkus-kafka-producer/Dockerfile -t <producer-image-name> .
docker build -f quarkus-kafka-consumer/Dockerfile -t <consumer-image-name> .
```
2) Make sure you have a k8s cluster running (in my case, I use docker-desktop cluster)
3) Go inside the *devops-files/devops-rumpup-poc-helm-charts/charts/quarkus-kafka-producer-helm-chart/values.yaml* and set the <producer-image-name> under the path deployment>containers>name
4) Go inside the *devops-files/devops-rumpup-poc-helm-charts/charts/quarkus-kafka-consumer-helm-chart/values.yaml* and set the <consumer-image-name> under the path deployment>containers>name
5) Go inside the *devops-files/devops-rumpup-poc-helm-charts* folder and deploy the application in the cluster using the following helm command
```
helm install <application-name> .
```
Note that this command will install also a nginx-ingress controller, please make sure that all pods are up and running!

## MongoDb
### Access MongoClient

1) Open a terminal, and execute the following command in order to open a bash terminal inside the mongo client container
```
kubectl exec deployment/mongo-client -it -- //bin//bash
```
2) Then login to mongo with the following command
```
mongosh --host mongo-nodeport-svc --port 27017 -u <username> -p <password>
```
Note that username and password are defined in the MongoDatabase's secret file under the path *devops-files/devops-rumpup-poc-helm-charts/charts/middleware/templates/mongo-db.yaml*
