# catalog-service

## Running PostgresSQL as a container

```shell
docker run -d \
--name polar-postgres \
-e POSTGRES_USER=user \
-e POSTGRES_PASSWORD=password \
-e POSTGRES_DB=polardb_catalog \
-p 5432:5432 \
postgres:14.4
```
* if you want to stop the container you can use `docker stop polar-postgres`
* If you want to start it up again you can use `docker start polar-postgres`
* If you need to recreate the container you can first remove it `docker rm -fv polar-postgres` and then recreate it. `docker run`

** Building and pushing to github registry
```bash
./gradlew bootBuildImage \
--imageName ghcr.io/<your_github_username>/catalog-service \
--publishImage \
-PregistryUrl=ghcr.io
-PregistryUsername=<your_github_username> \
-PregistryToken=<your_github_token>
```