FROM maven:3-eclipse-temurin-17 as builder
RUN mkdir /list-product-service
WORKDIR /list-product-service
COPY . .
RUN mvn clean package -DskipTests -q

FROM eclipse-temurin:17
COPY --from=builder /list-product-service/target/list-product-service-0.0.1-SNAPSHOT.jar list-product-service.jar
CMD ["java","-jar","list-product-service.jar"]