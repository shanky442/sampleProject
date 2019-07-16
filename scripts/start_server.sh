echo "inside start server"
nohup java -jar /var/myapp/game-0.1.0.jar >/var/log/game.log 2>&1 &