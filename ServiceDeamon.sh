#!/bin/sh
JAVA_HOME=/usr/java/jdk1.8.0_65/
JSVC=/root/commons-daemon/jsvc
USER=root

DAEMON_HOME=/root/commons-daemon
PID_FILE=$DAEMON_HOME/daemon.pid
OUT_FILE=$DAEMON_HOME/daemon.out
#ERR_FILE=$DEAMON_HOME/deamon.err

CLASSPATH=$DAEMON_HOME/commons-daemon-1.0.15.jar:$DAEMON_HOME/daemon-1.0-SNAPSHOT.jar

MAIN_CLASS=com.cafe24.daemon.DaemonMain

case "$1" in
        start)
        #
        # Start Deamon
        #
        rm -f $OUT_FILE
        $JSVC   \
        -user $USER     \
        -java-home      $JAVA_HOME      \
        -pidfile        $PID_FILE       \
        -outfile        $OUT_FILE       \
        -errfile        $OUT_FILE       \
        -cp     $CLASSPATH      \
        $MAIN_CLASS

        exit $?
        ;;

        stop)
        #
        # Stop Daemon
        #
        $JSVC   \
        -stop   \
        -nodetach       \
        -java-home      $JAVA_HOME      \
        -pidfile        $PID_FILE       \
        -outfile        $OUT_FILE       \
        -errfile        $OUT_FILE       \
        -cp     $CLASSPATH      \
        $MAIN_CLASS
        exit $?
        ;;

        *)

        echo "[Usage] TestDaemon.sh start | stop"
        exit 1;;
esac