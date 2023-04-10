FROM mizzouceri/tomcat-mvn-java11:V1
MAINTAINER Sai Preethi Induri "six2h@umsystem.edu"

RUN apt-get -y update && apt-get -y install lsb-release && apt-get install net-tools nano
RUN apt-get -y install default-mysql-server maven
WORKDIR /root 
ADD ./ /root/Covid19-website
WORKDIR /root/Covid19-website
#RUN mvn clean package
#RUN cp /root/Covid19-website/target/*.war /usr/local/tomcat/webapp/Scholarteamfinder.war
