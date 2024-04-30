docker system prune

Write-Host "Prepating for network"
docker network create -d bridge my_network
Write-Host "Network is ready"

Write-Host "Prepating for broker"
cd D:\projects\java\Emergency-notification-system
docker build -t my-rabbitmq:tag -f  src/main/resources/docker/rabbitmq/Dockerfile .
docker run -d --network my_network --name my-rabbitmq -p 5672:5672 -p 15672:15672 my-rabbitmq:tag
Write-Host "Broker is ready"

Write-Host "Prepating for database"
cd D:\projects\java\Emergency-notification-system
docker build -t postgres-master:tag -f src/main/resources/docker/database/master/Dockerfile .
docker run -d --network my_network --name postgres-master -p 15432:15432 postgres-master:tag
Write-Host "Database is ready"

Write-Host "Prepating java-apps"
cd D:\projects\java\Emergency-notification-system\ENS-load-balancer
mvn clean install
docker build -t load-balancer-network:tag -f src/main/docker/Dockerfile .

cd D:\projects\java\Emergency-notification-system\ENS-api-gateway
mvn clean install
docker build -t api-gateway-network:tag -f src/main/docker/Dockerfile .

cd D:\projects\java\Emergency-notification-system\ENS-request-service
mvn clean install
docker build -t request-service-network:tag -f src/main/docker/Dockerfile .

docker run -d --network my_network --name load-balancer-network -p 8761:8761 load-balancer-network:tag
docker run -d --network my_network --name api-gateway-network -p 8765:8765 api-gateway-network:tag
docker run -d --network my_network --name request-service-network-inst1 -p 0:8771 request-service-network:tag
#docker run -d --network my_network --name request-service-network-inst2 -p 0:8771 request-service-network:tag

Write-Host "Java-apps are ready"
Write-Host "Wait about a minute..."
