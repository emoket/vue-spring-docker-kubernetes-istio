# vue-spring-docker-kubernetes-istio

## Skill-set
- Vue.js
- Spring boot
- Node.js
- BFF pattern
- Docker
- Kubernetes
- istio

## Command

단독 docker build/run 부터 docker-compose up/down 그리고 Kubernetes

### Docker

```sh
docker build . -t emoket/frontend-vue:1.0.0
docker build . -t emoket/bff:1.0.0
docker build . -t emoket/backend-node:1.0.0
docker build . -t emoket/backend-spring:1.0.0

docker run --name backend-node -p 8083:8083 -d emoket/backend-node:1.0.0
docker run --name backend-spring -p 8082:8082 -d emoket/backend-spring:1.0.0
docker run --name bff -d -p 8081:8081 -e 'BACKEND_SPRING_HOST=backend-spring' -e 'BACKEND_SPRING_PORT=8082' -e 'BACKEND_NODE_HOST=backend-node' -e 'BACKEND_NODE_PORT=8083' --link backend-node --link backend-spring emoket/bff:1.0.0
docker run --name frontend-vue -p 8080:8080 -d --link bff emoket/frontend-vue:1.0.0
```

### Docker Compose

```sh
```
