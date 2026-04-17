FROM maven:3-eclipse-temurin-17 as builder
RUN mkdir /gateway-service
WORKDIR /gateway-service
COPY . .
RUN mvn clean package -DskipTests -q

FROM eclipse-temurin:17
COPY --from=builder /gateway-service/target/gateway-service-0.0.1-SNAPSHOT.jar gateway-service.jar
CMD ["java","-jar","gateway-service.jar"]