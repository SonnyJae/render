# 1단계: 소스에서 jar 빌드 (서버에서 git pull 후 바로 빌드 가능)
FROM gradle:8.12-jdk17 AS build
WORKDIR /src
COPY . .
RUN gradle bootJar --no-daemon -x test

# 2단계: 실행 이미지
FROM azul/zulu-openjdk:17-jre
ENV TZ=Asia/Seoul
# 로그 한글 깨짐 방지
ENV LANG=C.UTF-8
RUN apt-get update && apt-get install -y --no-install-recommends tzdata \
    && ln -sf /usr/share/zoneinfo/Asia/Seoul /etc/localtime \
    && echo "Asia/Seoul" > /etc/timezone \
    && rm -rf /var/lib/apt/lists/*
COPY --from=build /src/build/libs/*.jar /app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
