# chỉnh định jdk cần sử dụng
FROM openjdk:17-jdk-alpine
# chỉnh định folder để container chạy
WORKDIR /app
# copy file .jar từ folder build ( folder target/claim-manager-0.0.1-SNAPSHOT.jar ) vao folder /app chạy của container
COPY target/claim-manager-0.0.1-SNAPSHOT.jar claim-manager-0.0.1-SNAPSHOT.jar
# Chỉ định port
EXPOSE 8080
# cmd để chạy app spring boot
ENTRYPOINT ["java","-jar","claim-manager-0.0.1-SNAPSHOT.jar"]