FROM bellsoft/liberica-openjdk-alpine-musl
COPY ./target/prometeus_plus-0.0.1-SNAPSHOT.jar .
CMD ["java","-jar","prometeus_plus-0.0.1-SNAPSHOT.jar"]

#docker build -t prometheus_plus:latest .