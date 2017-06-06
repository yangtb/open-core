#!/bin/sh
  
SERVER=/opt/software/opencore
cd $SERVER

case "$1" in  

  start)  
    nohup java -Xmx384m -jar opencore.jar -Xdebug -Xnoagent -Xrunjdwp:transport=dt_socket,address=63323,server=y,suspend=n >/dev/null 2>&1 &
    echo $! > $SERVER/opencore.pid
    ;;  
  
  stop)  
    kill `cat $SERVER/opencore.pid`
    rm -rf $SERVER/opencore.pid
    ;;  
  
  restart)  
    $0 stop  
    sleep 1  
    $0 start  
    ;;  
  
  *)  
    echo "Usage: run.sh {start|stop|restart}"  
    ;;  
  
esac 
exit 0
