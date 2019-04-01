- [1. 개요](#1-%EA%B0%9C%EC%9A%94)
  - [1.1. Skill-set](#11-skill-set)
  - [1.2. 폴더 설명](#12-%ED%8F%B4%EB%8D%94-%EC%84%A4%EB%AA%85)
- [2. 작업 흐름](#2-%EC%9E%91%EC%97%85-%ED%9D%90%EB%A6%84)
  - [2.1. 로컬 개발 환경](#21-%EB%A1%9C%EC%BB%AC-%EA%B0%9C%EB%B0%9C-%ED%99%98%EA%B2%BD)
  - [2.2. docker 환경](#22-docker-%ED%99%98%EA%B2%BD)
    - [2.2.1. docker build](#221-docker-build)
    - [2.2.2. docker run](#222-docker-run)
  - [2.3. Kubernetes 환경](#23-kubernetes-%ED%99%98%EA%B2%BD)

# 1. 개요

## 1.1. Skill-set
- Vue.js
- Spring boot
- Node.js
- BFF pattern
- Docker
- Kubernetes
- istio

## 1.2. 폴더 설명

| 폴더명               | 용도                          | 개발환경    |
| -------------------- | -------------------------- | ----------- |
| backend-node   | send a node greeting message     | node.js |
| backend-spring | send a spring greeting message   | Spring boot    |
| bff            | gateway (backend for frontend)   | Spring boot |
| frontend-vue   | integrate all micro services     | Vue.js |

# 2. 작업 흐름

> 로컬 개발 환경 > docker (compose) 환경 > kubenetes 환경 > + istio

## 2.1. 로컬 개발 환경

각자 사용하는 IDE나 CLI command를 이용하여 구동시키되 (이왕이면) backend-xxx > bff > frontend-vue 순으로 구동시킨다.

## 2.2. docker 환경

### 2.2.1. docker build

Dockerfile은 Node 프로젝트의 경우 `root` 폴더에, Spring boot 프로젝트의 경우 `src/main/docker` 폴더 내 위치해 있다.

#### Node 프로젝트 docker build

> backend-node / frontend-vue

```sh
docker build . -t emoket/frontend-vue:1.0.0
docker build . -t emoket/backend-node:1.0.0
```

#### Spring boot 프로젝트 docker build

> backend-spring / bff

docker-maven-plugin 을 이용하여 빌드

```sh
mvn clean package docker:build
```

### 2.2.2. docker run

> docker 환경에서 테스트 시 서비스 간 호출을 할 수 있도록 `--link` 옵션을 적용하는 것이 핵심!

``` sh
docker run --name backend-node -p 8083:8083 -d emoket/backend-node:1.0.0
docker run --name backend-spring -p 8082:8082 -d emoket/backend-spring:1.0.0
docker run --name bff -d -p 8081:8081 -e 'BACKEND_SPRING_HOST=backend-spring' -e 'BACKEND_SPRING_PORT=8082' -e 'BACKEND_NODE_HOST=backend-node' -e 'BACKEND_NODE_PORT=8083' --link backend-node --link backend-spring emoket/bff:1.0.0
docker run --name frontend-vue -p 8080:8080 -d --link bff emoket/frontend-vue:1.0.0
```

### 2.2.3. docker-compose up

> 컨테이너가 많아질 경우 docker run 명령어를 일일이 수행하는 것에는 한계가 있기에 docker-compose 를 사용한다.

```sh
docker-compose up -d
```

- \-d Detached mode: Run containers in the background

## 2.3. Kubernetes 환경

> helm chart 를 이용하여 모든 구성을 한 번에 설치
