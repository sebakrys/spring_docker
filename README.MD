
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