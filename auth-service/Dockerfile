FROM maven:3-eclipse-temurin-17 as builder
RUN mkdir /auth-service
WORKDIR /auth-service
COPY . .
RUN mvn clean package -DskipTests -q

FROM eclipse-temurin:17
COPY --from=builder /auth-service/target/auth-service-0.0.1-SNAPSHOT.jar auth-service.jar
CMD ["java","-jar","auth-service.jar"]