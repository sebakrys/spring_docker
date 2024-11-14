# Spring Docker Application

This project is a **Spring Boot** application designed for containerized deployment using **Docker** and **Kubernetes**. It includes a complete setup for local development, testing, and production deployment.

## Table of Contents

- [Getting Started](#getting-started)
- [Prerequisites](#prerequisites)
- [Project Structure](#project-structure)
- [Building and Running](#building-and-running)
  - [Using Docker](#using-docker)
  - [Using Docker Compose](#using-docker-compose)
  - [Using Kubernetes](#using-kubernetes)
- [API Documentation](#api-documentation)
- [Testing](#testing)
  - [Postman](#postman)
- [Contributing](#contributing)
- [License](#license)

---

## Getting Started

This project is a template for microservice applications using Spring Boot. It can be deployed as a standalone service or integrated into a Kubernetes cluster.

### Features

- Spring Boot with RESTful APIs
- Docker and Docker Compose for containerization
- Kubernetes deployment support
- Maven for dependency management
- Postman collections for API testing

---

## Prerequisites

Before running the project, ensure you have the following installed:

- **Java 17** or higher
- **Maven 3.8+**
- **Docker 24+**
- **Docker Compose 2+**
- **Kubernetes CLI (kubectl)**
- **Postman** (for API testing)

---

## Project Structure

```plaintext
spring_docker-master/
├── .gitignore                # Git ignore file
├── Dockerfile                # Docker configuration for building the container
├── docker-compose.yml        # Docker Compose setup for multi-container setup
├── k8s/                      # Kubernetes YAML files for deployment
│   ├── deployment.yaml
│   ├── service.yaml
├── mvnw                      # Maven wrapper for portability
├── mvnw.cmd                  # Maven wrapper for Windows
├── pom.xml                   # Maven configuration
├── postman/                  # Postman API collections
│   ├── collection.json
├── src/                      # Source code of the application
│   ├── main/
│   │   ├── java/             # Java code
│   │   └── resources/        # Configuration files
│   └── test/                 # Unit and integration tests
└── README.MD                 # Documentation (this file)
```

---

## Building and Running

### Using Docker

1. Build the Docker image:
   ```bash
   docker build -t spring-docker-app .
   ```

2. Run the container:
   ```bash
   docker run -p 8080:8080 spring-docker-app
   ```

### Using Docker Compose

1. Start the services:
   ```bash
   docker-compose up
   ```

2. Stop the services:
   ```bash
   docker-compose down
   ```

### Using Kubernetes

1. Apply the deployment files:
   ```bash
   kubectl apply -f k8s/deployment.yaml
   kubectl apply -f k8s/service.yaml
   ```

2. Check the running pods:
   ```bash
   kubectl get pods
   ```

3. Forward port to access the service:
   ```bash
   kubectl port-forward svc/spring-docker-service 8080:8080
   ```

---

## API Documentation

### Endpoints

| Method | Endpoint            | Description         |
|--------|----------------------|---------------------|
| GET    | `/api/health`        | Health check        |
| POST   | `/api/resource`      | Create resource     |
| GET    | `/api/resource/{id}` | Get resource by ID  |

API specifications can be found in the [Postman collection](postman/collection.json).

---

## Testing

### Unit and Integration Tests

Run the tests using Maven:

```bash
mvn test
```

### Postman

Import the provided Postman collection from the `postman/` directory to test the API endpoints.



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

To deploy new image with the same name use:

```kubectl rollout restart deployment aplikacja```
