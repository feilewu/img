TAG=$1
docker pull pfxuresources/img:"$TAG"
containerId=`docker ps | grep img-serivce | awk '{print $1}'`
if [ -n "$containerId" ]
    then
      docker stop "$containerId"
      docker rm "$containerId"
fi

docker rmi `docker images | grep none | awk '{print $3}'`

docker run -it -d -p 9527:9527 \
        -v /root/.img:/root/.img \
        -v /root/.img/config:/opt/img/application/config \
        --name img-serivce \
        pfxuresources/img:"$TAG"


