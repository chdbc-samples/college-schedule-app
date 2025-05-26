@echo off
setlocal

REM Встановлення значень за замовчуванням, якщо не надано
if "%VERSION%"=="" set "VERSION=0.3.0-SNAPSHOT"
if "%GITHUB_OWNER%"=="" set GITHUB_OWNER=chdbc-samples
if "%APP_SERVICE_NAME%"=="" set "APP_SERVICE_NAME=college-schedule-app"
if "%APP_SERVICE_PLAN%"=="" set "APP_SERVICE_PLAN=college-schedule-plan"
if "%RESOURCE_GROUP%"=="" set "RESOURCE_GROUP=college-schedule-rg"
if "%IMAGE_NAME%"=="" set "IMAGE_NAME=college-schedule-app"

REM Перевірка наявності Azure CLI
where az >nul 2>&1

REM Скрипт для розгортання <APP_SERVICE_NAME> в Azure App Service.
REM
REM Перед запуском:
REM 1. Встановіть Azure CLI: https://aka.ms/azure-cli-download
REM 2. Увійдіть в Azure: az login
REM 3. Переконайтеся, що Azure App Service створено та налаштовано для отримання образів з GHCR.
REM    (одноразове налаштування для App Service):
REM
REM    az provider register --namespace Microsoft.Web
REM
REM   дочекайтесь створення підписки (можливо, це займе кілька хвилин):
REM    az provider show --namespace Microsoft.Web --query "registrationState"
REM    
REM    має повернути "Registered" коли все готово.
REM
REM    az appservice plan create `
REM      --name "<APP_SERVICE_PLAN>" `
REM      --resource-group "<RESOURCE_GROUP>" `
REM      --sku B1 `
REM      --is-linux

REM    az group create `
REM      --name "college-schedule-rg" `
REM      --location "westeurope"
REM
REM az webapp create `
REM  --name "<APP_SERVICE_PLAN>" `
REM  --plan "<APP_SERVICE_PLAN>" `
REM  --resource-group "college-schedule-rg" `
REM  --container-registry-url "https://ghcr.io" `
REM  --container-image-name "ghcr.io/<GITHUB_OWNER>/<IMAGE_NAME>:<VERSION>" `
REM  --container-registry-user "<YOUR_GITHUB_USERNAME>" `
REM  --container-registry-password "<YOUR_GITHUB_PAT_WITH_READ_PACKAGES_SCOPE>"


set "DOCKER_IMAGE_TAG=ghcr.io/%GITHUB_OWNER%/%IMAGE_NAME%:%VERSION%"

echo Розгортання %APP_SERVICE_NAME% версії: %VERSION%
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