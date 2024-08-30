@echo off

:: Check if running as administrator
net session >nul 2>&1
if %errorlevel% neq 0 (
    echo This script requires administrative privileges. Please run it as an administrator.
    pause
    exit
 /b 1
)

:: Check if Python is installed
python --version >nul 2>&1
if errorlevel 1 (
    echo Python is not installed. Installing the latest version...
    powershell -Command "Invoke-WebRequest -Uri https://www.python.org/ftp/python/latest/python-latest-amd64.exe -OutFile python-latest.exe"
    start /wait python-latest.exe /quiet InstallAllUsers=1 PrependPath=1
    del python-latest.exe
)

:: Prompt for PostgreSQL username and password
set /p POSTGRES_USER="Enter PostgreSQL username (if already installed, use your existing username): "
set /p POSTGRES_PASSWORD="Enter PostgreSQL password (if already installed, use your existing password): "

:: Run the Python script, passing the username and password as arguments
python setup\postgres.py %POSTGRES_USER% %POSTGRES_PASSWORD%

:: Pause to allow the user to see the output
pause