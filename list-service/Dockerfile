FROM maven:3-eclipse-temurin-17 as builder
RUN mkdir /list-service
WORKDIR /list-service
COPY . .
RUN mvn clean package -DskipTests -q

FROM eclipse-temurin:17
COPY --from=builder /list-service/target/list-service-0.0.1-SNAPSHOT.jar list-service.jar
CMD ["java","-jar","list-service.jar"]