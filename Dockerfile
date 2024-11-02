# Usa a imagem base do OpenJDK 17 com o JDK
FROM openjdk:17-jdk-slim

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia o JAR da aplicação para o contêiner
COPY target/*.jar app.jar

# Expõe a porta 8080, que é a porta padrão do Spring Boot
EXPOSE 8080

# Comando para executar a aplicação
CMD ["java", "-jar", "app.jar"]