docker pull pfxuresources/img:latest

containerId=`docker ps | grep img-serivce | awk '{print $1}'`
if [ -n "$containerId" ]
    then
      docker stop "$containerId"
      docker rm "$containerId"
fi

docker run -it -d -p 9527:9527 \
        -v /home/root/.img:/home/root/.img \
        -v /home/root/.img/config:/opt/img/application/config \
        --name img-serivce \
        pfxuresources/img


