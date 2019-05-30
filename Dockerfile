FROM jboss/wildfly
 
# Set Postgresql env variables
ENV DB_HOST postgres
ENV DB_PORT 5432
ENV DB_NAME bookstore
ENV DB_USER maradona
ENV DB_PASS maradona
 
ENV DS_NAME BookstoreDS
ENV JNDI_NAME java:jboss/datasources/BookstoreDS
 
USER root
 
ADD https://jdbc.postgresql.org/download/postgresql-42.2.4.jar /tmp/postgresql-42.2.4.jar
 
WORKDIR /tmp
COPY config_files/wildfly-command.sh ./
COPY config_files/module-install.cli ./
RUN sed -i -e 's/\r$//' ./wildfly-command.sh
RUN chmod +x ./wildfly-command.sh
RUN ./wildfly-command.sh \
    &&  rm -rf $JBOSS_HOME/standalone/configuration/standalone_xml_history/
 
# Create Wildfly admin user
RUN $JBOSS_HOME/bin/add-user.sh admin admin --silent
 
# Set the default command to run on boot
# This will boot WildFly in the standalone mode and bind to all interface
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]