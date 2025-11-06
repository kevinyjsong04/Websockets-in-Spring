@echo off

echo.
echo =============================================
echo   WebSocket Sequence Demo - Starting...
echo =============================================
echo.

if "%JAVA_HOME%"=="" (
    echo [INFO] JAVA_HOME not set. Setting to default location...
    set "JAVA_HOME=C:\Program Files\Java\jdk-17"
)

echo [INFO] Using Java from: %JAVA_HOME%
echo.

call gradlew.bat bootRun

pause
