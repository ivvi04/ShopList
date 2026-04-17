FROM maven:3-eclipse-temurin-17 as builder
RUN mkdir /server-service
WORKDIR /server-service
COPY . .
RUN mvn clean package -DskipTests -q

FROM eclipse-temurin:17
COPY --from=builder /server-service/target/server-service-0.0.1-SNAPSHOT.jar server-service.jar
CMD ["java","-jar","server-service.jar"]