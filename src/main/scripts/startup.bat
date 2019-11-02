@echo off

set "CURRENT_DIR=%~dp0.."
set "APP_ENCRYPTION_PASSWORD=MyS3cr3t!"

java -jar -Dapp.home=%CURRENT_DIR% "%~dp0\..\lib\facultad-app.jar"
