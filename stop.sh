#!/bin/bash

echo 'Begin stop'

cd ./client1 && ./app.sh stop && sleep 2 && cd ..
cd ./client2 && ./app.sh stop && sleep 2 && cd ..

cd ./simpleservice1 && ./app.sh stop && sleep 2 && cd ..
cd ./simpleservice2 && ./app.sh stop && sleep 2 && cd ..
cd ./simpleservice3 && ./app.sh stop && sleep 2 && cd ..

cd ./configserver1 && ./app.sh stop && sleep 2 && cd ..
cd ./configserver2 && ./app.sh stop && sleep 2 && cd ..

cd ./admin1 && ./app.sh stop && sleep 2 && cd ..
cd ./admin2 && ./app.sh stop && sleep 2 && cd ..

cd ./eurekaserver1 && ./app.sh stop && sleep 2 && cd ..
cd ./eurekaserver2 && ./app.sh stop && sleep 2 && cd ..

echo 'Done stop'


