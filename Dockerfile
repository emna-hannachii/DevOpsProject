FROM openjdk:11
EXPOSE 8082
ADD target/*.jar 5ARCTIC5-G3-DevOps.jar
ENTRYPOINT ["java","-jar","/5ARCTIC5-G3-DevOps.jar"]