@echo off
diff\cmp.exe -s build\output1.txt build\ref_output1.txt
IF %ERRORLEVEL%==0 ECHO Teszt1 (input1.txt) kimenete helyes
IF NOT %ERRORLEVEL%==0 ECHO Teszt1 (input1.txt) kimeneteben HIBA van!

diff\cmp.exe -s build\output2.txt build\ref_output2.txt
IF %ERRORLEVEL%==0 ECHO Teszt2 (input2.txt) kimenete helyes
IF NOT %ERRORLEVEL%==0 ECHO Teszt2 (input2.txt) kimeneteben HIBA van!

diff\cmp.exe -s build\output3.txt build\ref_output3.txt
IF %ERRORLEVEL%==0 ECHO Teszt3 (input3.txt) kimenete helyes
IF NOT %ERRORLEVEL%==0 ECHO Teszt1 (input3.txt) kimeneteben HIBA van!

diff\cmp.exe -s build\output4.txt build\ref_output4.txt
IF %ERRORLEVEL%==0 ECHO Teszt4 (input4.txt) kimenete helyes
IF NOT %ERRORLEVEL%==0 ECHO Teszt4 (input4.txt) kimeneteben HIBA van!

diff\cmp.exe -s build\output5.txt build\ref_output5.txt
IF %ERRORLEVEL%==0 ECHO Teszt5 (input5.txt) kimenete helyes
IF NOT %ERRORLEVEL%==0 ECHO Teszt5 (input5.txt) kimeneteben HIBA van!

diff\cmp.exe -s build\output6.txt build\ref_output6.txt
IF %ERRORLEVEL%==0 ECHO Teszt6 (input6.txt) kimenete helyes
IF NOT %ERRORLEVEL%==0 ECHO Teszt6 (input6.txt) kimeneteben HIBA van!

diff\cmp.exe -s build\output7.txt build\ref_output7.txt
IF %ERRORLEVEL%==0 ECHO Teszt7 (input7.txt) kimenete helyes
IF NOT %ERRORLEVEL%==0 ECHO Teszt7 (input7.txt) kimeneteben HIBA van!

pause