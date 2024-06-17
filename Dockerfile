FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar vitamincode_java5.jar
ENTRYPOINT ["java","-jar","/vitamincode_java5.jar"]
EXPOSE 8080