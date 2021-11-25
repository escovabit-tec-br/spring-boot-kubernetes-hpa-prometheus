# Docker

Aqui estão as informações de como fazer o build da aplicação

## Como fazer o build

No diretorio spring-boot/app-hpa/ chamar a linha do docker file:

```bash
docker build --build-arg SOURCE_PATH=. --build-arg JAR_NAME=app-hpa-0.0.1-SNAPSHOT.jar -t escovabit/app-hpa:latest -f ../../docker/Dockerfile .
```

## Como rodar local

```bash
docker run -e JAVA_APP="-Xms64m -Xmx64m" -p 8080:8080 escovabit/app-hpa:latest
```
