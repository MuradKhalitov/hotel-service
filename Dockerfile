#FROM openjdk:17.0.2-jdk-slim-buster
#COPY build build
#ENTRYPOINT ["java","-jar","/build/libs/HotelBooking-0.0.1-SNAPSHOT.jar"]


FROM openjdk:17.0.2-jdk-slim-buster
WORKDIR /app
COPY target/HotelBooking-0.0.1-SNAPSHOT.jar /app/HotelBooking-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/app/HotelBooking-0.0.1-SNAPSHOT.jar"]
