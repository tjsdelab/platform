rm -r ~/apache-tomcat-7.0.55/webapps/platform*
sleep 2
ant
sleep 2
cp /home7/qilongyin/code/platform/war/platform.war  /home7/qilongyin/apache-tomcat-7.0.55/webapps/

/home7/qilongyin/apache-tomcat-7.0.55/bin/shutdown.sh
sleep 2
/home7/qilongyin/apache-tomcat-7.0.55/bin/startup.sh

