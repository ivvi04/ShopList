FROM maven:latest as builder
RUN mkdir /product-service
WORKDIR /product-service
COPY . .
#RUN mvn clean install
RUN mvn clean package -DskipTests -q

FROM openjdk:17-oracle
COPY --from=builder /product-service/target/product-service-0.0.1-SNAPSHOT.jar product-service.jar
CMD ["java","-jar","product-service.jar"]