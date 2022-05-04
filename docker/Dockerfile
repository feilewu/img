FROM openjdk:8

RUN mkdir -p /opt/img/application
COPY img-application/target/img-application-*.jar /opt/img/application
COPY docker/start.sh /opt/img/application
WORKDIR /opt/img/application
CMD ["sh", "start.sh"]
