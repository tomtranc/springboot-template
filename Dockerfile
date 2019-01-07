FROM registry.access.redhat.com/jboss-webserver-3/webserver31-tomcat7-openshift
MAINTAINER tomtranc

USER root

ENV DATA_DIR /app-root/runtime/data
ENV TMP_DIR /tmp
ENV LOG_DIR /opt/webserver/logs
ENV CATALINA_HOME /opt/webserver
ENV GC_MAX_METASPACE_SIZE 512
ENV JAVA_OPTIONS -XX:MaxMetaspaceSize=512m

COPY docker-resources/mysql57-community-release-el7-11.noarch.rpm /
RUN yum -y localinstall /mysql57-community-release-el7-11.noarch.rpm
RUN yum install -y mysql

COPY target/${project.build.finalName}.war $JWS_HOME/webapps/ROOT.war

COPY docker-resources/bootstrap.sh /bootstrap.sh
RUN chmod +x /bootstrap.sh

EXPOSE 8000

ENTRYPOINT ["/bootstrap.sh"]
