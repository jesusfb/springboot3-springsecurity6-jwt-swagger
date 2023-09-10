FROM maven:4.0 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -Dmaven.test.skip=true

FROM amazoncorretto:17-alpine
MAINTAINER JesusFigueroa
COPY --from=builder /app/target/security-0.0.1-SNAPSHOT.jar /usr/local/lib/app.jar
EXPOSE 80
ENTRYPOINT ["java", "-jar", "/usr/local/lib/app.jar"]
