FROM maven:3-eclipse-temurin-17 as builder
RUN mkdir /product-service
WORKDIR /product-service
COPY . .
RUN mvn clean package -DskipTests -q

FROM eclipse-temurin:17
COPY --from=builder /product-service/target/product-service-0.0.1-SNAPSHOT.jar product-service.jar
CMD ["java","-jar","product-service.jar"]