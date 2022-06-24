FROM openjdk:11

COPY ../libs/ /usr/src/app
WORKDIR /usr/src/app
RUN javac Main.java
CMD ["java", "Main"]