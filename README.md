# Spring Demo
###Demo application
This is an Demo application built using postgres as back end and keycloak for jwt oauth2 authorization

Check docker compose file to see all the target apps(springdemo, postgre)
### Run app and postgress containers using docker compose
* To Start: docker compose up
* To Stop: docker compose down

#### API
```
curl -X GET http://localhost:8090/employees && echo
curl -X POST -H 'Content-type:application/json' http://localhost:8090/employees -d '{"firstName": "Alexandar","lastName": "Grahambel","role": "Scientist","salary": 123330}'
curl -X PUT -H 'Content-type:application/json' http://localhost:8090/employees/1 -d '{"firstName": "Test","lastName": "User","role": "TestRole","salary": 199999}'
curl -X DELETE http://localhost:8090/employees/3 && echo
```
####Access swagger ui for api details:
http://localhost:8090/swagger-ui.html

##Individual commands to start each container
###Run Spring demo using below commands
```
docker build -t springdemo .
docker run -p 8090:8090 springdemo
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
###Keyclock Authorization server
````
docker run -p 8080:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:17.0.0 start-dev
````
####Certs
```
keytool -genkey -alias tomcat -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore keystore.p12 -validity 3650
```
