docker system prune

cd D:\projects\java\Emergency-notification-system\ENS-load-balancer
mvn clean install
docker build -t load-balancer-network:tag -f src/main/docker/Dockerfile .

cd D:\projects\java\Emergency-notification-system\ENS-api-gateway
#mvn clean install
docker build -t api-gateway-network:tag -f src/main/docker/Dockerfile .

cd D:\projects\java\Emergency-notification-system\ENS-request-service
#mvn clean install
docker build -t request-service-network:tag -f src/main/docker/Dockerfile .

docker network create -d bridge my_network
docker run -d --network my_network --name load-balancer-network -p 8761:8761 load-balancer-network:tag
docker run -d --network my_network --name api-gateway-network -p 8765:8765 api-gateway-network:tag
docker run -d --network my_network --name request-service-network-inst1 -p 0:8771 request-service-network:tag
docker run -d --network my_network --name request-service-network-inst2 -p 0:8771 request-service-network:tag

Write-Host "Java-applications are ready"

cd D:\projects\java\Emergency-notification-system
docker build -t postgres-master:tag -f src/main/resources/docker/database/master/Dockerfile .
docker run -d --network my_network --name postgres-master -p 15432:5432 postgres-master:tag

Write-Host "Wait about a minute..."
