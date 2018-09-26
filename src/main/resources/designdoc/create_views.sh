#! /bin/bash

username=demo
password=password
hostname=127.0.0.1
port=8092
bucketname=demo


curl -X PUT -u ${username}:${password} -H 'Content-Type: application/json' http://${hostname}:${port}/${bucketname}/_design/car -d @car
