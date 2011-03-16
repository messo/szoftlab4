@echo off
cd src
javadoc logsim logsim.log logsim.model logsim.model.component logsim.model.component.impl logsim.model.skeleton -d ..\documents
cd..
if not errorlevel 1 echo Dokumentum generalas sikeres volt. 
pause