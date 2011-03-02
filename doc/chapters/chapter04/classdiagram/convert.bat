@ECHO OFF

PATH=%PATH%;C:\Program Files\Inkscape

for /f %%a IN ('dir /b *.svg') do call inkscape --export-pdf=%%~na.pdf %%~na.svg