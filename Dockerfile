FROM java:8
VOLUME /tmp
ADD demo-0.0.1-SNAPSHOT.jar /bysj.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/bysj.jar"]





