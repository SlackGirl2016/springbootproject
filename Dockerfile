FROM openjdk:8-jre-alpine

COPY wine-0.0.1-SNAPSHOT.war app.war

EXPOSE 8888

ENTRYPOINT ["java","-jar","/app.war"]
