# Fase de Construção
FROM maven:3.8.4-openjdk-17 as build
ENV HOME=/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME
ADD . $HOME

RUN mvn -f $HOME/pom.xml clean package

# Fase de Empacotamento e Configuração do Nginx
FROM eclipse-temurin:17-jre-jammy

# Copia o JAR empacotado da fase de construção
ARG JAR_FILE=/usr/app/target/*.jar

COPY --from=build $JAR_FILE /app/runner.jar


# Define a porta em que a aplicação estará ouvindo
EXPOSE 8086

# Inicia a aplicação Java e o Nginx
CMD ["sh", "-c", "java -jar /app/runner.jar"]