@echo off
set C="C:\Program Files\Java\jdk1.6.0_23\bin\"
mkdir build
cd src
%C%\javac -d ..\build logsim\Proto.java
cd..
if not errorlevel 1 echo Forditas sikeres
pause