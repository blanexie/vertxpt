## 编译打包
docker run --rm -u gradle \
-v "$PWD/../":/home/gradle/project \
-w /home/gradle/project/vxpt-eurake \
gradle:7-jdk11 gradle assemble

## 删除旧镜像
docker rmi vxpt-eurake:1.0

## 重新打包镜像
docker build -t vxpt-eurake:1.0 .

## 启动镜像
docker run --rm --name eurake -p 9090:9090 vxpt-eurake:1.0