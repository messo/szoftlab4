@echo off
set C="C:\Program Files\Java\jdk1.6.0_21\bin\"
cd src
%C%\javac logsim\Skeleton.java
cd..
if not errorlevel 1 echo Forditas sikeres
pause