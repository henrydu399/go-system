FROM openjdk:17-alpine
VOLUME /tmp
EXPOSE 8081
RUN mkdir -p /log
ADD ./target/eureka.jar eureka.jar
ADD ./src/main/resources/application.properties application.properties
ENTRYPOINT ["java" ,"-Djava.security.egd=file:/dev/./urandom --spring.config.location=file:/application.properties","-jar","/eureka.jar"]
