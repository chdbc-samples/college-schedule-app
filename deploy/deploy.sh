#!/bin/bash

# Script to run the college-schedule-app with Docker Compose
# Usage: ./deploy.sh [version]

# Set default version if not provided
VERSION=${1:-"latest"}
GITHUB_REPOSITORY=${GITHUB_REPOSITORY:-"your-username/college-schedule-app"}

# Export environment variables for docker-compose
export VERSION=$VERSION
export GITHUB_REPOSITORY=$GITHUB_REPOSITORY

echo "Deploying college-schedule-app version: $VERSION"

# Pull the latest images
docker-compose pull

# Start the containers
docker-compose up -d

# Check deployment status
echo "Checking deployment status..."
sleep 10
docker-compose ps

echo "Deployment completed. Application should be available at http://localhost:8080"
echo "MongoDB is available at localhost:27017"
echo "To view logs: docker-compose logs -f"
echo "To stop: docker-compose down"
