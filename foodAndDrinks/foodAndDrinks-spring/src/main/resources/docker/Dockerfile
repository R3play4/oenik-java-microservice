FROM openjdk:11

# Default to UTF-8 file.encoding
ENV LANG C.UTF-8

RUN mkdir -p /opt
ADD C:\OE NIK\OENIK_II\2022-1\microservices\oenik-java-microservice\foodAndDrinks\foodAndDrinks-spring\target\foodAndDrinks-spring.jar /opt/foodAndDrinks-spring.jar


EXPOSE 8080

ENTRYPOINT java -jar /opt/foodAndDrinks-spring.jar -Djava.net.preferIPv4Stack=true