#!/bin/bash
# Script to build the Docker image for college-schedule-app
# Usage: ./build-docker-image.sh [version] [repository]

# Set default values if not provided
VERSION=${1:-"0.2.0-SNAPSHOT"}
GITHUB_REPOSITORY=${2:-$(git config --get remote.origin.url | sed 's/.*github.com[:\/]\(.*\)\.git/\1/')}

echo "Building Docker image for college-schedule-app version: $VERSION"
echo "Repository: $GITHUB_REPOSITORY"

# Check if app.jar exists
if [ ! -f "app.jar" ]; then
  echo "Error: app.jar not found in the current directory."
  echo "Please make sure to copy the JAR file from your build or GitHub Packages first."
  exit 1
fi

# Build the Docker image
docker build -t ghcr.io/${GITHUB_REPOSITORY}/college-schedule-app:${VERSION} .

# Optional: Tag as latest as well
docker tag ghcr.io/${GITHUB_REPOSITORY}/college-schedule-app:${VERSION} ghcr.io/${GITHUB_REPOSITORY}/college-schedule-app:latest

echo "Docker image built successfully."
echo "To push to GitHub Packages, run:"
echo "docker push ghcr.io/${GITHUB_REPOSITORY}/college-schedule-app:${VERSION}"
echo "docker push ghcr.io/${GITHUB_REPOSITORY}/college-schedule-app:latest"
