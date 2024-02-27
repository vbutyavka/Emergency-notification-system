IMAGE_NAME=plugin-load-balancer

pushd ../../

D:\\projects\\java\\Emergency-notification-system\\ENS-load-balancer\\mvnw spring-boot:build-image -DskipTests=true -Dspring-boot.build-image.imageName=$IMAGE_NAME

docker image ls | grep $IMAGE_NAME
