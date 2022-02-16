# Spring Demo

### Run app and postgress containers using docker compose
* To Start: docker compose up
* To Stop: docker compose down

#### API
```
curl -X GET http://localhost:8080/employees && echo
curl -X POST -H 'Content-type:application/json' http://localhost:8080/employees -d '{"firstName": "Alexandar","lastName": "Grahambel","role": "Scientist","salary": 123330}'
curl -X PUT -H 'Content-type:application/json' http://localhost:8080/employees/1 -d '{"firstName": "Test","lastName": "User","role": "TestRole","salary": 199999}'
curl -X DELETE http://localhost:8080/employees/3 && echo
```
####Access swagger ui for api details:
http://localhost:8080/swagger-ui.html

##Individual commands to start each container
###Run Spring demo using below commands
```
docker build -t springdemo .
docker run -p 8080:8080 springdemo
```
###Run postgresql 
* docker run -p 5432:5432 --name postgres-db -e POSTGRES_PASSWORD=password -d postgres
####alternate
```
docker run -d \
  --name postgres-db \
  -p 5432:5432 \
  -e POSTGRES_PASSWORD=password \
  -e PGDATA=/var/lib/postgresql/data/pgdata \
  -v postgress:/var/lib/postgresql/data \
  postgres
```
####Certs
```
keytool -genkey -alias tomcat -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore keystore.p12 -validity 3650
```
