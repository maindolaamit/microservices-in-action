# Getting Started

[Open Swagger UI](http://localhost:8080/swagger-ui.html)
```curl
curl -X 'GET' \
  'http://localhost:8080/v1/organization/1/license/1' \
  -H 'accept: application/json'
```

### Docker
use `dockerfile-maven-plugin` to build your docker image based on the [Dockerfile](./dockerfile) in the project root.
```shell
mvn clean package
mvn package dockerfile:build
```
Manually create a docker image
```shell
docker build -t optima/license-service .
```

Run the docker image
```shell
docker run -p 8080:8080 optima/license-service
```


