# flight-api


⚙️ 1. 项目结构

    flight-api：Spring Boot 后端项目。
    flight-client：React 前端项目。

⚙️ 2. 后端依赖

    Java 17
    Spring Boot 3.2.0
    Spring Data JPA
    Spring Web
    Spring Security
    Lombok
    MySQL 数据库

⚙️ 3. 前端依赖

    Node.js 18
    React 18
    Axios 1.7.7
    React Router 6.23.0

    安装依赖：

cd flight-client
npm install

    启动前端：

npm start

    访问：URL_ADDRESS    访问：http://localhost:3000
    注意：确保后端服务已经启动，并且配置了正确的数据库连接。

⚙️ 4. 本地运行流程

    启动 Spring Boot 后端：

cd flight-api
./mvnw spring-boot:run

    启动 React 前端：

cd flight-client
npm start

    Spring Boot 启在：http://localhost:8080

    React 启在：http://localhost:3000

访问前端页面，应该可以看到：

React Frontend 🚀
Hello from Spring Boot!

⚙️ 5. Mock运行流程

    启动 Spring Boot 后端：

cd flight-api
./mvnw spring-boot:run

    生成openapi.json,放到后端工程根目录下：
http://localhost:8080/v3/api-docs

    启动 Mock 服务：
npm install -g @stoplight/prism-cli
prism mock ./openapi.json --port 4010 --dynamic --cors