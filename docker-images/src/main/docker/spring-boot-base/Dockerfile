FROM afrunt/spring-cloud-examples-java-base
RUN mkdir /deployment

ENV APP_FILE_NAME app.jar
ENV MEM_MAX 256m
ENV JAVA_OPTIONS ""
ENV APP_OPTIONS ""


WORKDIR /deployment


CMD java -Xmx$MEM_MAX $JAVA_OPTIONS -jar $APP_FILE_NAME $APP_OPTIONS
