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


#creates a container
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)
#builds an image and disposes the container

#-------------completes the stage-1 of building the Docker image ----------------------------#


#brand new image
FROM openjdk:11.0.13-jre-slim-buster as stage


#argument
ARG DEPENDENCY=/app/target/dependency


# Copy the dependency application file from builder stage artifact
COPY --from=builder ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=builder ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=builder ${DEPENDENCY}/BOOT-INF/classes /app


EXPOSE 8222

ENTRYPOINT ["java", "-cp", "app:app/lib/*", "com.bajajfinserve.orders.OrdersApiApplication"]

