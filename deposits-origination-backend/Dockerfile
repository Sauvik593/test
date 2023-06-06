#
# Build stage
#
FROM maven:3.8.3-openjdk-17 AS build
COPY deposit-origination /home/deposit-origination
#COPY pom.xml /home/deposit-origination
RUN mvn -f /home/deposit-origination/pom.xml clean package

#
# Package stage
#
FROM eclipse-temurin:17.0.7_7-jre-alpine
COPY --from=build /home/deposit-origination/target/deposit-origination-0.0.1-SNAPSHOT.jar /usr/local/lib/deposit-origination.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/deposit-origination.jar"]

