FROM maven:latest as builder
RUN mkdir /user-service
WORKDIR /user-service
COPY . .
#RUN mvn clean install
RUN mvn clean package -DskipTests -q

FROM openjdk:17-oracle
COPY --from=builder /user-service/target/user-service-0.0.1-SNAPSHOT.jar user-service.jar
CMD ["java","-jar","user-service.jar"]