FROM openjdk:17.0.2-jdk-slim-buster
COPY target/HotelBooking-0.0.1-SNAPSHOT.jar /HotelBooking-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/HotelBooking-0.0.1-SNAPSHOT.jar"]
