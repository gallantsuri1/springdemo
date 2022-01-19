FROM openjdk:11

COPY target/springdemo-0.0.1-SNAPSHOT.jar springdemo.jar

EXPOSE 8080:8080

ENTRYPOINT ["java","-jar", "springdemo.jar"]