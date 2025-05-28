@echo off

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

REM AKS cluster created successfully!
kubectl get nodes