@ECHO OFF

for /f %%a IN ('dir /b *.pdf') do call pdfcrop %%~na.pdf %%~na.pdf