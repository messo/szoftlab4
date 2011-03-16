@echo off
set PATH=%PATH%;C:\Program Files\Java\jdk1.6.0_21\bin\
cd src
javac logsim\Skeleton.java
cd..
if not errorlevel 1 echo Forditas sikeres
pause