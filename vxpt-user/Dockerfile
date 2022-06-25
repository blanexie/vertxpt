FROM openjdk:11

COPY build/libs/ /usr/src/app
WORKDIR /usr/src/app
EXPOSE 9091

ENTRYPOINT ["java", "-jar", "vxpt-user-unspecified.jar"]