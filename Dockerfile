# write the instructions to build the docker image
# this will internally pull the operating system
FROM openjdk:11-jdk-slim as builder

# setup the working directory
WORKDIR /app

#copies file/directory from src location to destination location
# source location will the codebase
# destination location will be the docker image
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

#creates a container
RUN chmod +x ./mvnw && ./mvnw -B dependency:go-offline
#builds an image and disposes the container

# copy will not create a container
COPY src src

#creates a container
RUN ./mvnw package -DskipTests
#builds an image and disposes the container

ENTRYPOINT [ "sleep", "60" ]