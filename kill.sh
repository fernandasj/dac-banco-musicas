docker kill app
docker rm app
docker rmi fernanda/app
docker kill banco
docker rm banco
docker rmi fernanda/banco

# docker rmi -f $(docker image ls fernanda/* -q)
# docker kill $(docker container ls -a -q)
# docker rm banco
# docker rm app
