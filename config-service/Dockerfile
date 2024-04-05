FROM maven:latest as builder
RUN mkdir /config-service
WORKDIR /config-service
COPY . .
#RUN mvn clean install
RUN mvn clean package -DskipTests -q

FROM openjdk:17-oracle
COPY --from=builder /config-service/target/config-service-0.0.1-SNAPSHOT.jar config-service.jar
CMD ["java","-jar","config-service.jar"]