@echo off
set C="C:\Program Files\Java\jdk1.6.0_24\bin\"
mkdir build
cd src
%C%\javac -d ..\build logsim\GuiController.java
cd..
if not errorlevel 1 echo Forditas sikeres
pause