@echo off
set C="C:\Program Files\Java\jdk1.6.0_24\bin\"
cd src
%C%\javadoc logsim logsim logsim.model logsim.model.component ^
logsim.model.component.impl -d ..\documents
cd..
if not errorlevel 1 echo Dokumentum generalas sikeres volt. 
pause