#!/bin/sh
# Version: 1.0
# Date: 12.01.2024
#=========================================
# Script starts LibraryLinks application.
#=========================================

mvn clean package

cd ./docker

docker-compose up --build