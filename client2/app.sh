#!/bin/bash
JARFile="./target/client2-1.0.0-SNAPSHOT.jar"
PIDFile="app.pid"
SPRING_OPTS="-DLOG_FILE=application.log"
PID="-1"
NOPID="-1"

function print_process {
  echo $(<"$PIDFile")
}

function check_pid_file {
  if [ -f $PIDFile ]
  then
    PID=$(print_process)
    return 0;
  else
    PID="-1"
    return 1;
  fi
}

function check_pid_running {
  check_pid_file
  if [ "$PID" == "$NOPID" ]
  then
    return 1
  fi
  if ps -p $PID > /dev/null
  then
    return 0
  else
    return 1
  fi
}

case "$1" in

status)
  if check_pid_running
  then
    echo "Process is running (" $PID ")"
  else
    echo "Process not running"
  fi
;;

stop)

if check_pid_running
then
  kill -TERM $PID
  echo -ne "Stopping Process"
  NOT_KILLED=1
  for i in {1..30}; do
    if check_pid_running
    then
      echo -ne "."
      sleep 1
    else
      NOT_KILLED=0
    fi
  done
  echo
  if [ $NOT_KILLED = 1 ]
  then
    echo "Cannot kill process " $PID
    exit 1
  fi
  echo "Process stopped"
else
   echo "Process already stopped"
fi
;;

start)
  if check_pid_running
  then
    echo "Process already running"
    exit 1
  fi
  nohup java -Xmx256m -jar $JARFile $SPRING_OPTS >/dev/null 2>&1 &
  echo "Process started"
;;

debug)
  if check_pid_running
  then
    echo "Process already running"
    exit 0
  fi
  nohup java -Xmx256m -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=5005,suspend=n -jar $JARFile $SPRING_OPTS >/dev/null 2>&1 &

  echo "Process started"
;;

restart)
  $0 stop
  if [ $? = 1 ]
  then
    exit 1
  fi
  $0 start
;;

*)
  echo "Usage: $0 {start|stop|restart|debug|status}"
  exit 1

esac

exit 0