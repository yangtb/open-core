@echo off  
set localdir=%~dp0  
cd ..
call java -Xmx384m -jar opencore.jar
pause 