# Etap 1: Budowanie aplikacji
FROM maven:3-eclipse-temurin-22-jammy AS build

# Ustawiamy katalog roboczy w obrazie
WORKDIR /app

# Kopiujemy plik pom.xml i pliki źródłowe do katalogu roboczego
COPY pom.xml .
COPY src ./src

# Uruchamiamy komendę Maven, aby zbudować projekt i stworzyć plik JAR
RUN mvn clean package -DskipTests

# Etap 2: Tworzenie finalnego obrazu
FROM openjdk:22-jdk-bullseye

# Ustawiamy katalog roboczy w finalnym obrazie
WORKDIR /app

# Kopiujemy zbudowany plik JAR z poprzedniego etapu
COPY --from=build /app/target/test-0.0.1-SNAPSHOT.jar .

# Otwieramy port 8080
EXPOSE 8080

# Uruchamiamy aplikację
ENTRYPOINT ["java", "-jar", "test-0.0.1-SNAPSHOT.jar"]
