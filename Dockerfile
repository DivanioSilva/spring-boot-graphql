FROM openjdk:8-jdk-alpine
#  Hang the local folder in the current container
VOLUME /tmp
ARG JAR_FILE
#  Copy files to container
ADD target/${JAR_FILE} app.jar
#  Declare ports that need to be exposed
EXPOSE 8080
#  Configure the commands to execute after the container starts
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

