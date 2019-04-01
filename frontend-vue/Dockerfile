FROM node:10.15.3-alpine

# 이미지 안에 애플리케이션 코드를 넣기 위해 디렉터리를 생성
WORKDIR /usr/src/app

# 앱 의존성 설치
# 가능한 경우(npm@5+) package.json과 package-lock.json을 모두 복사하기 위해 와일드카드를 사용
# 작업 디렉터리 전체가 아닌 package.json 파일만을 복사하고 있는데, 이는 캐시된 Docker 레이어의 장점을 활용하기 위함
COPY package*.json ./

RUN npm install
# 프로덕션을 위한 코드를 빌드하는 경우
# RUN npm ci --only=production

# 앱 소스 추가
COPY . .

EXPOSE 8080

CMD [ "npm", "run", "serve" ]