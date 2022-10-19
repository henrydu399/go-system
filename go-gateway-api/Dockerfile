FROM openjdk:17-alpine
VOLUME /tmp
EXPOSE 8082
ADD ./target/gateway.jar gateway.jar
ADD ./src/main/resources/application.yml application.yml
ENTRYPOINT ["java" ,"-Djava.security.egd=file:/dev/./urandom --spring.config.location=file:/application.properties","-jar","/gateway.jar"]
