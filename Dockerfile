# 1. Используем официальный JDK 17 на Alpine Linux (легковесный)
FROM eclipse-temurin:17-jdk-alpine

# 2. Создаём рабочую директорию внутри контейнера
WORKDIR /app

# 3. Копируем собранный jar в контейнер
# Убедись, что путь совпадает с твоим target/названием jar после сборки
COPY target/SmartStorage-0.0.1-SNAPSHOT.jar app.jar

# 4. Открываем порт, который использует Spring Boot (по умолчанию 8080)
EXPOSE 8080

# 5. Команда запуска приложения
ENTRYPOINT ["java", "-jar", "app.jar"]
