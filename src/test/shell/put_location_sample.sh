#!/bin/bash

PLATE="MRW-01"

long=$(($RANDOM / 1000))
lat=$(($RANDOM / 1000))

curl \
    -X PUT \
    -v -N \
    -H "X-API-Key: eddd4796c4271d874f674d16cb0d7623" \
    -H "Content-Type: application/json" \
    -H "Accept: application/json" \
    -d "{\"longitude\": $long, \"latitude\": $lat}" \
    http://localhost:8080/rest-demo/rest/vehicle/$PLATE/locations 

exit $?
