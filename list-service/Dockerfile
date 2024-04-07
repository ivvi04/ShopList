FROM maven:latest as builder
RUN mkdir /list-service
WORKDIR /list-service
COPY . .
RUN mvn clean package -DskipTests -q

FROM openjdk:17-oracle
COPY --from=builder /list-service/target/list-service-0.0.1-SNAPSHOT.jar list-service.jar
CMD ["java","-jar","list-service.jar"]