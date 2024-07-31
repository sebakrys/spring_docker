FROM openjdk:22

COPY ./target/test-0.0.1-SNAPSHOT.jar /usr/src/test/test.jar

WORKDIR /usr/src/test

EXPOSE 8080

CMD ["java", "-jar", "test.jar"]