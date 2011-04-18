@echo off
set C="C:\Program Files\Java\jdk1.6.0_23\bin\"
xcopy tesztek build
cd build
%C%\java logsim.Proto input1.txt output1.txt
%C%\java logsim.Proto input2.txt output2.txt
%C%\java logsim.Proto input3.txt output3.txt
%C%\java logsim.Proto input4.txt output4.txt
%C%\java logsim.Proto input5.txt output5.txt
%C%\java logsim.Proto input6.txt output6.txt
%C%\java logsim.Proto input7.txt output7.txt
cd..
echo Tesztek lefutottak
PAUSE