FROM tomcat:10.1.16
MAINTAINER "paulo.rqsf@gmail.com"
COPY target/*.war /usr/local/tomcat/mywebapps/
COPY ROOT.xml /usr/local/tomcat/conf/Catalina/localhost/
EXPOSE 8080

CMD ["catalina.sh", "run"]
