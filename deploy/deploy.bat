@echo off
setlocal

REM Script to run the college-schedule-app with Docker Compose
REM Usage: deploy.bat [version]

REM Set default version if not provided
if "%VERSION%"=="" set VERSION=latest

REM Set default repository if not provided
if "%GITHUB_REPOSITORY%"=="" set GITHUB_REPOSITORY=

REM Set environment variables for docker-compose
set VERSION=%VERSION%
set GITHUB_REPOSITORY=%GITHUB_REPOSITORY%

echo Deploying college-schedule-app version: %VERSION%

REM Pull the latest images
docker-compose pull

REM Start the containers
docker-compose up -d

REM Check deployment status
echo Checking deployment status...
timeout /t 10 /nobreak > nul
docker-compose ps

echo.
echo Deployment completed. Application should be available at http://localhost:8080
echo PostgreSQL is available at localhost:5432
echo To view logs: docker-compose logs -f
echo To stop: docker-compose down