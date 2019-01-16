FROM selenoid/vnc:chrome_70.0

USER root

RUN apt-get update && \
		apt-get -y install default-jre

COPY ./../entrypoint.sh /
COPY ./../../../server/target/remote-robot-server-1.0.jar /remote-robot-server/
#COPY ./server/target/remote-robot-server-1.0.jar /remote-robot-server/

USER selenium

EXPOSE 5555

ENTRYPOINT ["/entrypoint.sh"]


