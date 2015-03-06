#!/bin/bash

echo 'Begin start'

DIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )
CONFIG_SERVICE_DIR=$DIR/config
export CONFIG_SERVICE_DIR

echo 'Start eureka servers(cluster)'
cd ./eurekaserver1 && ./app.sh start && sleep 2 && cd ..
cd ./eurekaserver2 && ./app.sh start && sleep 2 && cd ..

echo 'wait 30 secods for eureka cluster to be up and ready'
for i in {0..30}; do echo -ne "$i"'\r'; sleep 1; done;

echo 'Start config servers(cluster)'
echo 'Use configDir: '$CONFIG_SERVICE_DIR
cd ./configserver1 && ./app.sh start && sleep 2 && cd ..
cd ./configserver2 && ./app.sh start && sleep 2 && cd ..

echo 'wait 30 secods for config cluster to be up and ready'
for i in {0..30}; do echo -ne "$i"'\r'; sleep 1; done;

echo 'Start admin UI instances'
cd ./admin1 && ./app.sh start && sleep 2 && cd ..
cd ./admin2 && ./app.sh start && sleep 2 && cd ..

echo 'wait 30 secods for admin UI instances to be up and ready'
for i in {0..30}; do echo -ne "$i"'\r'; sleep 1; done;

echo 'Start simple service instances'
cd ./simpleservice1 && ./app.sh start && sleep 2 && cd ..
cd ./simpleservice2 && ./app.sh start && sleep 2 && cd ..
cd ./simpleservice3 && ./app.sh start && sleep 2 && cd ..

echo 'Start client instances'
cd ./client1 && ./app.sh start && sleep 2 && cd ..
cd ./client2 && ./app.sh start && sleep 2 && cd ..

echo 'Done start'


