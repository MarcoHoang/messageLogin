# Sử dụng Java 17 JDK làm base image (nhẹ, Alpine Linux)
FROM eclipse-temurin:17-jdk-alpine

# Tạo thư mục chứa app
WORKDIR /app

# Copy file jar sau khi build vào image
ARG JAR_FILE=target/DemoWebSocket-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

# Expose cổng mặc định Spring Boot (8080 hoặc theo bạn cấu hình)
EXPOSE 8884

# Chạy ứng dụng
ENTRYPOINT ["java", "-jar", "app.jar"]
