help:
	@fgrep -h "##" $(MAKEFILE_LIST) | fgrep -v fgrep | sed -e 's/\\$$//' | sed -e 's/##//'
app:
	## Spring Demo - mvn build and docker build along docker compose up
	## Usages:
	## make all -- to run mvn build, docker build and compose up
	## make build -- to run "mvn clean install"
	## make docker -- to run "docker build -t springdemo ."
	## make up -- to run "docker compose down &&	docker compose up"
	## make down -- to run "docker compose down"

all: build docker up

build:
	mvn clean install

docker:
	docker build -t springdemo .

up:
	docker compose down
	docker compose up

down:
	docker compose down

