FROM openjdk:17-jdk-alpine
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY ./build/libs/dfa-server-0.0.1.jar ./app.jar
EXPOSE 8081
CMD ["java","-jar","app.jar"]