FROM openjdk:8u201-jdk-alpine

RUN mkdir -p /opt/img/application/config
COPY img-application/target/img-application-*.jar /opt/img/application
COPY docker/start.sh /opt/img/application
WORKDIR /opt/img/application
CMD ["sh", "start.sh"]
