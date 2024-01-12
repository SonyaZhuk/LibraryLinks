FROM openjdk:11
MAINTAINER szhuk

WORKDIR /app
ARG JAR_FILE=target/LibraryLinks-*[^stub].jar
COPY ${JAR_FILE} LibraryLinks.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar","/app/LibraryLinks.jar"]