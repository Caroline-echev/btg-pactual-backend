FROM openjdk:17-jdk-slim

WORKDIR /app

COPY funds-management/build/libs/funds-management-0.0.1-SNAPSHOT.jar .

EXPOSE 8090

ENTRYPOINT ["java", "-jar", "funds-management-0.0.1-SNAPSHOT.jar"]
