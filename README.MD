
## Docker: docker-compose.yml
To run docker compose file use:

```docker-compose up --build```

## Kubernetes using minikube

Run docker desktop

In powershell run:

```minikube start```

To check minikube status type:

```minikube status```

Move to catalogue with kubernetes configuration files:

```cd k8s```

Next to deploy all files which are located in one directory run:

```kubectl apply -f .```
