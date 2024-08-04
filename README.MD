
## Docker: docker-compose.yml
To run docker compose file use:

```docker-compose up --build```

## Kubernetes using minikube

Run docker desktop

In powershell run:

```minikube start```

To check minikube status type:

```minikube status```

Check Kubernetes context:

```kubectl config get-contexts```

```kubectl config use-context minikube```

To share local image with kubernetes use:

Powershell: ```minikube docker-env | Invoke-Expression```
And then build your docker image:
```docker build -t aplikacja:latest .```

Move to catalogue with kubernetes configuration files:

```cd k8s```

Next to deploy all files which are located in one directory run:

```kubectl apply -f .```

To forward port, that application will be accesible via for example POSTMAN use:

```kubectl port-forward aplikacja-57d7ddf548-ld7jm 8080:8080```(modify POD name)


Using different engine for minikube:
Enable-WindowsOptionalFeature -Online -FeatureName Microsoft-Hyper-V -All
minikube config set driver hyperv

Useful links:

https://stackoverflow.com/questions/71714919/unable-to-access-my-minikube-cluster-from-the-browser-because-you-are-using-a/71749078#71749078

https://stackoverflow.com/questions/76470764/why-i-cant-get-access-to-app-from-browser-with-kubernetes-minikube

## Kubernetes using Build-in Docker

Check Kubernetes context:

```kubectl config get-contexts```

```kubectl config use-context docker-desktop```

Move to catalogue with kubernetes configuration files:

```cd k8s```

Next to deploy all files which are located in one directory run:

```kubectl apply -f .```