FROM maven:3-eclipse-temurin-17 as builder
RUN mkdir /user-service
WORKDIR /user-service
COPY . .
RUN mvn clean package -DskipTests -q

FROM eclipse-temurin:17
COPY --from=builder /user-service/target/user-service-0.0.1-SNAPSHOT.jar user-service.jar
CMD ["java","-jar","user-service.jar"]