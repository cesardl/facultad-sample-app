#!/bin/sh

CURRENT_DIR=$(cd "`dirname $0`/.." && pwd)

java -jar -Dapp.home=$CURRENT_DIR ../lib/facultad-app.jar
