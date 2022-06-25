FROM openjdk:11

COPY build/libs/ /usr/src/app
WORKDIR /usr/src/app
EXPOSE 9090

ENTRYPOINT ["java", "-jar", "vxpt-eureka-unspecified.jar"]