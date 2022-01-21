FROM maven:3-jdk-8
WORKDIR ./
COPY ./ ./
RUN mvn package

ENTRYPOINT ["/bin/sh", "-c"]
CMD  [ "java -jar target/ApiGateway-0.0.1-SNAPSHOT.jar" ]
