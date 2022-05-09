# shell

Linux_ids=`ps -ef | grep img-application*.jar | grep -v "grep" | awk '{print $2}'`
echo $Linux_ids

for id in $Linux_ids
do
    kill -9 $id
    echo "killed $id"
done
# shellcheck disable=SC2046
# shellcheck disable=SC2164
# shellcheck disable=SC2006
cd `pwd`
if [ ! -d "deploy" ]; then
  mkdir back
fi


rm -rf /root/workspace/img/img-application*.jar
nohup java -jar /root/workspace/img/img-application*.jar >/dev/null 2>&1 &
