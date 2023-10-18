# devops-rumpup-poc
Repository to start to "play" with some DevOps technologies and tools like k8s, docker and helm.

## Hot to build in local
0) You need to have docker engine and k8s installed on your local machine
1) Build docker image with 
```docker 
docker build -f src/main/docker/Dockerfile.jvm -t quarkus/quarkus-app-jvm .
```
2) Make sure you have a k8s cluster running (in my case, I use docker-desktop cluster)
3) In order to have our ingress working, we need to deploy nginx-ingress controller 
```
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v1.6.4/deploy/static/provider/cloud/deploy.yaml
```
please make sure that the pods are running and everything is ok
3) Go inside the *devops-files/kubefiles* folder and deploy the application in the cluster using the following commands
```
kubectl apply -f ./namespace.yml
kubectl apply -f ./deployemnt.yml
kubectl apply -f ./service.yml
kubectl apply -f ./ingress.yml
```
