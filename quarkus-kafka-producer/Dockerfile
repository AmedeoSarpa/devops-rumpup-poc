####
# This Dockerfile is used in order to build a container that runs the Quarkus application in JVM mode
#
# Before building the container image run:
#
# ./mvnw package
#
# Then, build the image with:
#
# docker build -f quarkus-kafka-producer/Dockerfile -t <image name> .
#
# Then run the container using:
#
# docker run -i --rm -p 8080:8080 <image name>
#
# If you want to include the debug port into your docker image
# you will have to expose the debug port (default 5005 being the default) like this :  EXPOSE 8080 5005.
# Additionally you will have to set -e JAVA_DEBUG=true and -e JAVA_DEBUG_PORT=*:5005
# when running the container
#
# Then run the container using :
#
# docker run -i --rm -p 8080:8080 quarkus/quarkus-kafka-producer-jvm

# First stage: build the application
FROM maven:3.8.3-openjdk-17 AS build
COPY quarkus-kafka-producer/. /app
WORKDIR /app
RUN mvn package -DskipTests

# Second stage: execute the quarkus app
FROM registry.access.redhat.com/ubi8/openjdk-17:1.16
ENV LANGUAGE='en_US:en'
# We make four distinct layers so if there are application changes the library layers can be re-used
COPY --chown=185 --from=build /app/target/quarkus-app/lib/ /deployments/lib/
COPY --chown=185 --from=build /app/target/quarkus-app/*.jar /deployments/
COPY --chown=185 --from=build /app/target/quarkus-app/app/ /deployments/app/
COPY --chown=185 --from=build /app/target/quarkus-app/quarkus/ /deployments/quarkus/
EXPOSE 8080
USER 185
ENV JAVA_OPTS="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager"
ENV JAVA_APP_JAR="/deployments/quarkus-run.jar"

ENTRYPOINT [ "/opt/jboss/container/java/run/run-java.sh" ]

