export JAVA_HOME="/opt/bigdata/java/default"
export ZOOKEEPER_HOME="/opt/bigdata/zookeeper/default"
export HADOOP_HOME="/opt/bigdata/hadoop/default"
export HADOOP_PREFIX=${HADOOP_HOME}
export HADOOP_PID_DIR="/opt/bigdata/hadoop/data/hdfs/pid"
export HADOOP_LOG_DIR="/opt/bigdata/hadoop/data/logs"
export HADOOP_CONF_DIR="${HADOOP_HOME}/etc/hadoop"

export PATH=$PATH:$JAVA_HOME/bin:$ZOOKEEPER_HOME/bin:$HADOOP_HOME/bin:$HADOOP_HOME/sbin

