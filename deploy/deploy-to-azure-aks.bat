@echo off

REM Встановлення значень за замовчуванням, якщо не надано
if "%VERSION%"=="" set "VERSION=0.3.0-SNAPSHOT"
if "%GITHUB_OWNER%"=="" set GITHUB_OWNER=chdbc-samples
if "%RESOURCE_GROUP%"=="" set "RESOURCE_GROUP=college-schedule-rg"

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
REM    az provider register --namespace Microsoft.Web --wait
REM
REM    az group create `
REM      --name "college-schedule-rg" `
REM      --location "westeurope"

REM az provider register --namespace microsoft.insights --wait

REM Creating AKS cluster...

az aks create `
  --resource-group college-schedule-rg `
  --name college-schedule-aks `
  --node-count 2 `
  --node-vm-size Standard_B2s `
  --location westus2 `
  --generate-ssh-keys `
  --enable-cluster-autoscaler `
  --min-count 1 `
  --max-count 3

REM Getting AKS credentials...
az aks get-credentials `
    --resource-group college-schedule-rg `
    --name college-schedule-aks

REM Отримайте subscription ID
$subscriptionId = az account show --query id -o tsv

REM Створіть Service Principal
az ad sp create-for-rbac `
  --name "github-actions-aks" `
  --role "Azure Kubernetes Service Cluster User Role" `
  --scopes "/subscriptions/$subscriptionId/resourceGroups/college-schedule-rg/providers/Microsoft.ContainerService/managedClusters/college-schedule-aks" `
  --sdk-auth

REM Create a secret for GitHub Container Registry
REM Replace YOUR_GITHUB_USERNAME and YOUR_GITHUB_PAT with your actual GitHub username and Personal Access Token 
kubectl create secret docker-registry ghcr-secret `
  --docker-server=ghcr.io `
  --docker-username=YOUR_GITHUB_USERNAME `
  --docker-password=YOUR_GITHUB_PAT_WITH_READ_PACKAGES_SCOPE

REM AKS cluster created successfully!
kubectl get nodes

kubectl apply -f deploy/k8s/postgres-deployment.yml

kubectl wait --for=condition=ready pod -l app=postgres --timeout=300s

kubectl delete deployment college-schedule-app --ignore-not-found=true

kubectl wait --for=delete pod -l app=college-schedule-app --timeout=120s

kubectl apply -f deploy/k8s/app-deployment.yml

kubectl wait --for=condition=ready pod -l app=college-schedule-app --timeout=600s

kubectl get service college-schedule-app --output jsonpath='{.status.loadBalancer.ingress[0].ip}'
