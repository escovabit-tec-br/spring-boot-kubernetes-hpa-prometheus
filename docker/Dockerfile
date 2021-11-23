# build
FROM maven:3-jdk-11 AS build
ARG SOURCE_PATH

RUN mkdir /project
COPY $SOURCE_PATH /project
WORKDIR /project
RUN mvn clean package -DskipTests
  

# running
FROM openjdk:11-jdk-slim
ARG JAR_NAME

RUN mkdir /app
COPY --from=build /project/target/$JAR_NAME /app/java-application.jar
WORKDIR /app

CMD java $JAVA_DEFAULT $JAVA_SECURITY $JAVA_SIZE $JAVA_GC $JAVA_OPTIONS $JAVA_APP -jar /app/java-application.jar

EXPOSE 8080

