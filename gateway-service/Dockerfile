FROM maven:latest as builder
RUN mkdir /gateway-service
WORKDIR /gateway-service
COPY . .
#RUN mvn clean install
RUN mvn clean package -DskipTests -q

FROM openjdk:17-oracle
COPY --from=builder /gateway-service/target/gateway-service-0.0.1-SNAPSHOT.jar gateway-service.jar
CMD ["java","-jar","gateway-service.jar"]