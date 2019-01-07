#!/bin/bash

current_time=$(date "+%Y.%m.%d-%H.%M.%S")
cp ${CATALINA_HOME}/logs/bootstrap.logs ${DATA_DIR}/bootstrap${current_time}.log

# Deploy using container's webserver
#sh /opt/webserver/bin/launch.sh

# Deploy using embedded webserver inside jar/war
java -jar /deployment/ROOT.war