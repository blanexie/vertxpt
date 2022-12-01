## 编译打包
docker run --rm -u gradle \
-v "$PWD/../":/home/gradle/project \
-w /home/gradle/project/vxpt-eureka \
gradle:7-jdk11 gradle assemble

## 删除旧镜像
docker rmi vxpt-eureka:1.0

## 重新打包镜像
docker build -t vxpt-eureka:1.0 .

## 停止容器
docker stop eureka

## 删除容器
docker rm eureka

## 启动容器
docker run --rm -d  --name eureka -p 9090:9090 vxpt-eureka:1.0