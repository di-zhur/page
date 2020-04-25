FROM maven:3-jdk-11 as builder
VOLUME /tmp
EXPOSE 8080
ADD target/page-0.0.1-SNAPSHOT.jar page-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/page-0.0.1-SNAPSHOT.jar"]
