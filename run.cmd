@echo off

set db_profile=
set spring_options=

if "%1"=="mongodb" (
    echo Starting MongoDB...
    set db_profile=mongodb
    docker run -d -p 27917:27017 mongo:6.0 >nul 2>&1
    :checkmongodb
    echo|set /p="."
    ping -n 2 localhost >nul
    "C:\Program Files\mongosh\"mongosh localhost:27917 --eval "show collections" >nul 2>&1 || goto checkmongodb
    @REM "C:\Program Files\mongosh\"mongosh localhost:27917 --eval "db.measure.find^(^).forEach^(printjson^)"
) else (
    echo Starting PostgreSQL...
    set db_profile=jpa
    docker run -d -p 5432:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=pharma postgres:14.5 >nul 2>&1
    set PGPASSWORD=postgres
    createdb -h localhost -p 5432 -U postgres -w pharma
    :checksql
    echo|set /p="."
    ping -n 2 localhost >nul
    psql -h localhost -p 5432 -U postgres -d pharma -c "select 1" >nul 2>&1 || goto checksql
    @REM psql -h localhost -p 5432 -U postgres -d pharma -v -c "table mceasure;"
)

@REM set /p ready=Is database ready?

echo.
echo Starting application...
set spring_cmd=gradle clean bootRun -P%db_profile%  -Dspring.profiles.active=verify,%db_profile%
@REM echo %spring_cmd%
%spring_cmd%
