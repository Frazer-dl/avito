# https://hub.docker.com/layers/adoptopenjdk/library/adoptopenjdk/11.0.7_10-jdk-hotspot-bionic/images/sha256-05df284aea654234eb1de8d8346a0079c33ab03adda1262f92971c39388e99e8?context=explore
FROM adoptopenjdk:11.0.7_10-jdk-hotspot-bionic

ARG PROJECT_BUILD_NAME
ENV PROJECT_BUILD_NAME=${PROJECT_BUILD_NAME}
ENV JAR_FILE=target/assignment-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} ${JAR_FILE}

ENTRYPOINT exec java \
${JAVA_XMX:--Xmx128m} \
-Duser.home=${USER_HOME:-/var/avito} \
-jar ${JAR_FILE}