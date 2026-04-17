FROM maven:3-eclipse-temurin-17 as builder
RUN mkdir /config-service
WORKDIR /config-service
COPY . .
RUN mvn clean package -DskipTests -q

FROM eclipse-temurin:17
COPY --from=builder /config-service/target/config-service-0.0.1-SNAPSHOT.jar config-service.jar
CMD ["java","-jar","config-service.jar"]