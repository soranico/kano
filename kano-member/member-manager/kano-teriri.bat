@echo off
title TERIRI-PACKAGE
color 5
set teririBegin=%time%
set /A teririStartM=%teririBegin:~3,2%
set /A teririStartS=%teririBegin:~-5,2%
echo *****************************
echo   KANO-TERIRI-PACKAGE-START
echo *****************************
cd ../
set kano= %cd%
set teriri=kano-common
if exist %kano%\%teriri% (
	call mvn clean install
	cd %~dp0
	call mvn clean package
) else (	
	cd ../
	call mvn clean install
	cd %kano%
	call mvn clean install
	cd %~dp0
	call mvn clean package )
set teririEnd=%time%
set /A teririEndM=%teririEnd:~3,2%
set /A teririEndS=%teririEnd:~-5,2%
if %teririEndS% lss %teririStartS% (
	set /A teririEndS+=60
	set /A teririEndM-=1
)
set /A cost=(%teririEndM%-%teririStartM%)*60+(%teririEndS%-%teririStartS%)
echo *****************************
echo   KANO-TERIRI-PACKAGE-END
echo     COST-TIME = %cost% S
echo *****************************
pause