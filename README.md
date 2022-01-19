# Spring Demo

### Run app and postgress containers
* To Start: docker compose up
* To Stop: docker compose down

Access swagger ui for api details:
http://localhost:8080/swagger-ui.html

##Individual commands to start each container
###Run Spring demo using below commands
* docker build -t springdemo .
* docker run -p 8080:8080 springdemo

###Run postgresql 
* docker run -p 5432:5432 --name postgres-db -e POSTGRES_PASSWORD=password -d postgres
####alternate
* docker run -d \
  --name postgres-db \
  -p 5432:5432 \
  -e POSTGRES_PASSWORD=password \
  -e PGDATA=/var/lib/postgresql/data/pgdata \
  -v postgress:/var/lib/postgresql/data \
  postgres

