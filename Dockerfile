FROM tomcat
COPY /target/dac-banco-musicas-1.0-SNAPSHOT.war ${CATALINA_HOME}/webapps
VOLUME "/.local/share/apache-tomcat-8.0.27/testes"