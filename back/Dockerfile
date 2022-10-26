

FROM maven:3.8.5-openjdk-11 AS maven_build

COPY pom.xml /tmp/

COPY src /tmp/src/

WORKDIR /tmp/

RUN mvn package


FROM openjdk:11

EXPOSE 8080

CMD java -jar /data/ccee.jar

COPY --from=maven_build /tmp/target/ccee.jar /data/ccee.jar
