# 项目部署脚本

#### 数据库的部署
docker run -d --rm \
    --name postgreSql \
    -e POSTGRES_PASSWORD=7696634xzc \
    -e PGDATA=/var/lib/postgresql/data/pgdata \
    -v ~/postgres/data:/var/lib/postgresql/data \
    -p 5432:5432 \
    postgres
