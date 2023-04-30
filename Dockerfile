FROM openjdk:11-jdk

RUN apt-get update && \
    apt-get install -y maven

COPY . /root/ScholarTeamFinder

WORKDIR /root/ScholarTeamFinder

RUN mvn clean package

EXPOSE 9301

CMD ["java", "-jar", "target/ScholarTeamFinder-2.5.4.jar"]
