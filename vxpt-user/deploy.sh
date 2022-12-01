## 编译打包
docker run --rm -u gradle \
-v "$PWD/../":/home/gradle/project \
-w /home/gradle/project/vxpt-user \
gradle:7-jdk11 gradle assemble

## 删除旧镜像
docker rmi vxpt-user:1.0

## 重新打包镜像
docker build -t vxpt-user:1.0 .

## 停止容器
docker stop user

## 删除容器
docker rm user

## 启动容器
docker run --rm -d --name user -p 9090:9090 vxpt-user:1.0