FROM mayan31370/openjdk-alpine-with-chinese-timezone:8-jdk
ADD ianunei-noname.jar /

EXPOSE 8080
ENTRYPOINT [ "java","-jar","/ianunei-noname.jar"]
