FROM openjdk:8
EXPOSE 8080
ADD target/users-backend.jar users-backend.jar
ENTRYPOINT ["java","-jar","/users-backend.jar"]