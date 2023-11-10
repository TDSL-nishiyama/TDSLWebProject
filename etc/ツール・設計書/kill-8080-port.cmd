@echo off
:: 
:: Windows 8080 port kill command.
:: License EPL 2.0
:: Copyright (c) 2005- Shinji Kashihara (cypher256).
:: 
netstat -aon | find ":8080" | find "LISTENING"
IF %ERRORLEVEL% == 0 (
	for /f "tokens=5" %%a in ('netstat -aon ^| find ":8080" ^| find "LISTENING"') do taskkill /f /pid %%a 2> NUL
) ELSE (
    echo Port 8080 is not open.
)
timeout /nobreak 5
