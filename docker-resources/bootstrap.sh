#!/bin/bash

current_time=$(date "+%Y.%m.%d-%H.%M.%S")
cp ${CATALINA_HOME}/logs/bootstrap.logs ${DATA_DIR}/bootstrap${current_time}.log

sh /opt/webserver/bin/launch.sh