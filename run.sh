docker build -t fernanda/banco ./postgres
docker run -p 5435:5432 --name banco --privileged=true -d fernanda/banco 
mvn clean package
docker build -t fernanda/app .
docker run -p 8086:8080 --name app --link banco:host-banco  --privileged=true -d  -v $(pwd)/apps:/.local/share/apache-tomcat-8.0.27/testes fernanda/app 

