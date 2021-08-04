FROM adoptopenjdk:16-jre-hotspot
WORKDIR /opt/app
COPY target/nursingHome-0.0.1-SNAPSHOT.jar nursingHome.jar
CMD ["java", "-jar", "nursingHome.jar"]