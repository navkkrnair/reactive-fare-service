FROM openjdk:11-jre-alpine
#Alpine docker image doesn't have bash installed by default. 
#You will need to add following commands to get bash:
RUN apk add --no-cache bash
# Adding curl utility
RUN apk --no-cache add curl
ENV APPROOT="/app"
WORKDIR $APPROOT 
COPY build/libs/fare-service-1.0.jar $APPROOT
EXPOSE 8080
ENTRYPOINT ["java"]
CMD ["-jar","-Xmx512m","-Xms512m","-Djava.security.egd=file:/dev/./urandom", "fare-service-1.0.jar"]
