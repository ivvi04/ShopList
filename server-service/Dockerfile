FROM maven:latest as builder
RUN mkdir /server-service
WORKDIR /server-service
COPY . .
#RUN mvn clean install
RUN mvn clean package -DskipTests -q

FROM openjdk:17-oracle
COPY --from=builder /server-service/target/server-service-0.0.1-SNAPSHOT.jar server-service.jar
CMD ["java","-jar","server-service.jar"]