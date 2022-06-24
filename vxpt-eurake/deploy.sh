## 编译打包
docker run --rm -u gradle \
-v "$PWD/../":/home/gradle/project \
-w /home/gradle/project/vxpt-eurake \
gradle gradle assemble
