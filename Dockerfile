FROM openjdk:8-jre-alpine

COPY /build/libs/*.war app.war

EXPOSE 8888

ENTRYPOINT ["java","-jar","/app.war"]
