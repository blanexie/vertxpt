## 编译打包
docker run --rm -u gradle \
-v "$PWD/../":/home/gradle/project \
-w /home/gradle/project/vxpt-eurake \
gradle:7-jdk11 gradle assemble

## 删除旧镜像
docker rmi vxpt-eurake:1.0

## 重新打包镜像
docker build -t vxpt-eurake:1.0 .

## 停止容器
docker stop  eurake

## 删除容器
docker rm eurake

## 启动容器
docker run --rm -d  --name eurake -p 9090:9090 vxpt-eurake:1.0