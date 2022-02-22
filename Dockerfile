FROM adoptopenjdk:11-jdk
add ./build/libs/Discoman-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
