# 使用官方的Java 17环境作为基础镜像
FROM openjdk:17-jdk

# 添加维护者信息（可选）
LABEL maintainer="pwang25@ncsu.edu"

# 指定变量，简化之后的命令
ARG JAR_FILE=target/*.jar

# 将编译好的jar文件复制到容器中
COPY ${JAR_FILE} app.jar

# 暴露端口，确保与你的Spring Boot应用配置的端口一致
EXPOSE 8080

# 运行jar文件
ENTRYPOINT ["java","-jar","/app.jar"]


