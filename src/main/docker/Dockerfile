FROM openjdk:21
LABEL authors="egorm"

VOLUME /tmp
WORKDIR /app
ADD maven/product-service-0.0.1-SNAPSHOT.jar /app/productservice.jar
EXPOSE 6060
ENTRYPOINT ["java", "-jar", "productservice.jar"]