#!/bin/sh

CLASSPATH=./classes:./lib/commons-logging-1.2.jar:./lib/fluent-hc-4.4.jar:./lib/gson-2.2.4.jar
CLASSPATH=$CLASSPATH:./lib/httpclient-4.4.jar:./lib/httpclient-cache-4.4.jar:./lib/httpclient-win-4.4.jar:./lib/httpcore-4.4.jar
CLASSPATH=$CLASSPATH:./lib/httpmime-4.4.jar:./lib/logback-classic-1.1.2.jar:./lib/logback-core-1.1.2.jar:./lib/slf4j-api-1.7.7.jar

APPID=UrbanBot
CORE_URL=http://localhost:8080/cy-bss-core
USER_ID=cybss
PWD=cybss


echo 'CLASSPATH='$CLASSPATH

java -classpath "$CLASSPATH" org.cysoft.urbanbot.main.UrbanbotMain $APPID $CORE_URL $USER_ID $PWD
