FROM openjdk:17-oracle

WORKDIR /app

COPY build/libs/JWTNotes.jar /app

CMD ["java", "-jar", "JWTNotes.jar"]