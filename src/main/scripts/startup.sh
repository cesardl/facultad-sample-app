#!/bin/sh

CURRENT_DIR=$(cd "`dirname $0`/.." && pwd)
export APP_ENCRYPTION_PASSWORD=MyS3cr3t!

java -jar -Dapp.home=$CURRENT_DIR $CURRENT_DIR/lib/facultad-app.jar
