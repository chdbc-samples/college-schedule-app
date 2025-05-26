@echo off
setlocal

REM Скрипт для розгортання college-schedule-app в Azure App Service.
REM Використання: deploy-to-azure.bat [version] [github_owner] [app_service_name] [resource_group]
REM
REM Перед запуском:
REM 1. Встановіть Azure CLI: https://aka.ms/azure-cli-download
REM 2. Увійдіть в Azure: az login
REM 3. Переконайтеся, що Azure App Service створено та налаштовано для отримання образів з GHCR.
REM    (одноразове налаштування для App Service):
REM    az webapp config container set --name <APP_SERVICE_NAME> --resource-group <RESOURCE_GROUP> ^
REM      --docker-registry-server-url https://ghcr.io ^
REM      --docker-registry-server-user <YOUR_GITHUB_USERNAME> ^
REM      --docker-registry-server-password <YOUR_GITHUB_PAT_WITH_READ_PACKAGES_SCOPE>

REM Встановлення параметрів
set "CLI_VERSION=%~1"
set "CLI_GITHUB_OWNER=%~2"
set "CLI_APP_SERVICE_NAME=%~3"
set "CLI_RESOURCE_GROUP=%~4"

REM Встановлення значень за замовчуванням, якщо не надано
set "VERSION=%CLI_VERSION%"
if "%VERSION%"=="" set "VERSION=latest"

set "GITHUB_OWNER=%CLI_GITHUB_OWNER%"
if "%GITHUB_OWNER%"=="" (
    echo Помилка: Власника GitHub (GITHUB_OWNER) не вказано.
    echo Використання: deploy-to-azure.bat [version] [github_owner] [app_service_name] [resource_group]
    goto :eof
)

set "APP_SERVICE_NAME=%CLI_APP_SERVICE_NAME%"
if "%APP_SERVICE_NAME%"=="" set "APP_SERVICE_NAME=college-schedule-app"

set "RESOURCE_GROUP=%CLI_RESOURCE_GROUP%"
if "%RESOURCE_GROUP%"=="" set "RESOURCE_GROUP=college-schedule-rg"

set "IMAGE_NAME=college-schedule-app"
set "DOCKER_IMAGE_TAG=ghcr.io/%GITHUB_OWNER%/%IMAGE_NAME%:%VERSION%"

echo Розгортання college-schedule-app версії: %VERSION%
echo Власник образу GitHub: %GITHUB_OWNER%
echo App Service: %APP_SERVICE_NAME%
echo Група ресурсів: %RESOURCE_GROUP%
echo Повний тег образу: %DOCKER_IMAGE_TAG%
echo.

echo Оновлення конфігурації контейнера App Service...
az webapp config container set ^
    --name "%APP_SERVICE_NAME%" ^
    --resource-group "%RESOURCE_GROUP%" ^
    --docker-custom-image-name "%DOCKER_IMAGE_TAG%" ^
    --docker-registry-server-url "https://ghcr.io"

if errorlevel 1 (
    echo Помилка під час оновлення конфігурації контейнера.
    goto :eof
)

echo.
echo Рекомендується налаштувати змінні середовища (App Settings) в Azure Portal або за допомогою Azure CLI:
echo   az webapp config appsettings set --resource-group "%RESOURCE_GROUP%" --name "%APP_SERVICE_NAME%" --settings SPRING_DATASOURCE_URL="<your_db_url>" SPRING_DATASOURCE_USERNAME="<db_user>" ...
echo.
echo Перезапуск App Service для застосування змін...
az webapp restart --name "%APP_SERVICE_NAME%" --resource-group "%RESOURCE_GROUP%"

if errorlevel 1 (
    echo Помилка під час перезапуску App Service.
    goto :eof
)

echo.
echo Розгортання завершено. Застосунок повинен бути доступний за кілька хвилин.
echo URL застосунку (може знадобитися деякий час для оновлення DNS та запуску контейнера):
az webapp show --name "%APP_SERVICE_NAME%" --resource-group "%RESOURCE_GROUP%" --query "defaultHostName" -o tsv

echo.
echo Щоб переглянути логи: увійдіть в Azure Portal або використовуйте Azure CLI:
echo   az webapp log tail --name "%APP_SERVICE_NAME%" --resource-group "%RESOURCE_GROUP%"

endlocal