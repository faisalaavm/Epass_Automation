@echo off
cd /d "C:\Users\PearlSoft LT-125.DESKTOP-221H5FN\eclipse-workspace\Epass_Automation"
call mvn clean test-compile exec:java
pause