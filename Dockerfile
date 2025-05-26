# --- Stage 1: Build ứng dụng ---
FROM maven:3.9.6-eclipse-temurin-17 AS builder

# Tạo thư mục chứa source code
WORKDIR /app

# Copy toàn bộ mã nguồn vào image
COPY . .

# Build file JAR (bỏ qua test để nhanh hơn)
RUN mvn clean package -DskipTests


# --- Stage 2: Chạy ứng dụng ---
FROM eclipse-temurin:17-jdk-alpine

# Tạo thư mục chứa app
WORKDIR /app

# Copy file JAR từ stage 1 sang stage 2
COPY --from=builder /app/target/DemoWebSocket-0.0.1-SNAPSHOT.jar app.jar

# Mở cổng mặc định (sửa nếu bạn dùng cổng khác)
EXPOSE 8884

# Chạy ứng dụn
ENTRYPOINT ["java", "-jar", "app.jar"]
