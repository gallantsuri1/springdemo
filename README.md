# Spring Demo

###Run Spring demo using below commands
* docker build -t springdemo .
* docker run -p 8080:8080 springdemo



###Run postgresql 
* docker run -p 5432:5432 --name postgres-db -e POSTGRES_PASSWORD=password -d postgres
####alternate
* docker run -d \
  --name postgres-db \
  -e POSTGRES_PASSWORD=password \
  -e PGDATA=/var/lib/postgresql/data/pgdata \
  -v /custom/mount:/var/lib/postgresql/data \
  postgres
* 
