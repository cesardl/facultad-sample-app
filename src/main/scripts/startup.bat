@echo off

set "CURRENT_DIR=%~dp0.."

java -jar -Dapp.home=%CURRENT_DIR% "%~dp0\..\lib\facultad-app.jar"
