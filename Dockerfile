FROM adoptopenjdk/openjdk11:jdk-11.0.2.9-alpine

ENV APP_INSTALL_DIR /opt/app/

RUN mkdir -p ${APP_INSTALL_DIR}

ADD target/olx-postman-*.jar ${APP_INSTALL_DIR}/app.jar

EXPOSE 8080

WORKDIR ${APP_INSTALL_DIR}

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar", "--spring.profiles.active=prod"]