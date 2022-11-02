FROM carsharing/alpine-oraclejdk8-bash
MAINTAINER jcgarceso@liverpool.com.mx
ADD credito-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 7000
ENTRYPOINT ["java", "-jar","/app.jar"]