#!/bin/bash

CUSTOMER="MRW"
PLATE="$CUSTOMER-01"

API_KEY=$(echo -n $CUSTOMER | md5)

long=$(($RANDOM / 1000))
lat=$(($RANDOM / 1000))

curl \
    -X PUT \
    -v -N \
    -H "X-API-Key: $API_KEY" \
    -H "Content-Type: application/json" \
    -H "Accept: application/json" \
    -d "{\"longitude\": $long, \"latitude\": $lat}" \
    http://localhost:8080/rest-demo/rest/vehicle/$PLATE/locations 

exit $?
