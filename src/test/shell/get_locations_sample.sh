#!/bin/bash

CUSTOMER="MRW"
PLATE="$CUSTOMER-01"

API_KEY=$(echo -n $CUSTOMER | md5)

from=0
to=$(($(date +%s) * 1000))

curl \
    -X GET \
    -v -N \
    -H "X-API-Key: $API_KEY" \
    -H "Accept: application/json" \
    "http://localhost:8080/rest-demo/rest/vehicle/$PLATE/locations?fromUtcTs=$from&toUtcTs=$to"

exit $?
