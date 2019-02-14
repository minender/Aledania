FROM debian:stretch

RUN apt-get update && apt-get install software-properties-common dirmngr -y
RUN sh -c 'echo "deb http://ppa.launchpad.net/webupd8team/java/ubuntu trusty main" >> /etc/apt/sources.list' && sh -c 'echo "deb-src http://ppa.launchpad.net/webupd8team/java/ubuntu trusty main" >> /etc/apt/sources.list' &&\ 
    apt-key adv --no-tty --keyserver hkp://keyserver.ubuntu.com:80 --recv-keys EEA14886
RUN echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | debconf-set-selections && apt-get update && apt-get install -y oracle-java8-installer && rm -rf /var/lib/apt/lists/* && rm -rf /var/cache/oracle-jdk8-installer
RUN cd /home
RUN groupadd tomcat
RUN mkdir /opt/tomcat
RUN useradd -g tomcat -d /opt/tomcat -s /bin/nologin tomcat
RUN wget http://www-us.apache.org/dist/tomcat/tomcat-8/v8.5.34/bin/apache-tomcat-8.5.34.tar.gz && tar -zxvf apache-tomcat-*.tar.gz &&\ 
    mv apache-tomcat-8.5.34/* /opt/tomcat/ && rm -f apache-tomcat-8.5.34.tar.gz
RUN rm -r apache-tomcat-8.5.34 
RUN chown -R tomcat:tomcat /opt/tomcat/
RUN sed -i 's/<\/tomcat-users>/  <role rolename="admin-gui,manager-gui"\/>\n  <user username="admin" password="password" roles="manager-gui,admin-gui"\/>\n<\/tomcat-users>/g' /opt/tomcat/conf/tomcat-users.xml
RUN sed -i 's/0:0:0:0:0:0:0:1/0:0:0:0:0:0:0:1|ip1|ip2|ip3/g' /opt/tomcat/webapps/manager/META-INF/context.xml
RUN sed -i 's/0:0:0:0:0:0:0:1/0:0:0:0:0:0:0:1|ip1|ip2|ip3/g' /opt/tomcat/webapps/host-manager/META-INF/context.xml

COPY Aledania.war /opt/tomcat/webapps

EXPOSE 8080

CMD ["sh","/opt/tomcat/bin/catalina.sh", "run"]
