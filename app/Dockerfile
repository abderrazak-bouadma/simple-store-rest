# Build stage
FROM maven:3.9.4-amazoncorretto-21-debian
ENV MAVEN_OPTS -Xms1024m -Xmx2048m
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY . .
RUN mvn package

# Production stage
FROM amazoncorretto:21-alpine3.18
WORKDIR /app
COPY --from=0 /app/target/demo-0.0.1-SNAPSHOT.jar .
CMD ["java", "-jar", "demo-0.0.1-SNAPSHOT.jar","-DABSOLUTE_WORK_DIR=$ABSOLUTE_WORK_DIR"]

