FROM openjdk
WORKDIR passport
ADD target/job4j_passport-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT java -jar app.jar
