FROM tomcat:7-jre8

COPY tomcat-users.xml /usr/local/tomcat/conf/tomcat-users.xml
COPY h2-1.4.195.jar /usr/local/tomcat/lib/h2-1.4.195.jar