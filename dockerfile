FROM java:8-jre-alpine

VOLUME /tmp

EXPOSE 8080

COPY build/libs/image-rekognition-1.0.jar imageapp.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/imageapp.jar"]