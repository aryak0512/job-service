FROM bellsoft/liberica-openjdk-debian:21.0.5

WORKDIR /usr/share/app

COPY target/*.jar app.jar

CMD ["java", "-jar", "app.jar"]