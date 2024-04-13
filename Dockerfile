FROM openjdk:11
COPY . .
RUN apt-get update && apt-get install -y maven
RUN mvn clean install -DskipTests
CMD ["mvn","clean","test"]