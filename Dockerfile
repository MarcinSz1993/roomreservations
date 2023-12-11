FROM openjdk:17

COPY target/room_reservation_docker_app.jar /usr/app/

COPY src/main/resources/db/changelog /usr/app/src/main/resources/db/changelog

WORKDIR /usr/app

ENTRYPOINT ["java", "-jar", "room_reservation_docker_app.jar"]