FROM openjdk:18
COPY target/PseudoRandomFileGenerator-0.0.1-SNAPSHOT.jar PseudoRandomFileGenerator-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/PseudoRandomFileGenerator-0.0.1-SNAPSHOT.jar"]