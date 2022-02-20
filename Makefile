app:
	# Spring Demo - mvn build and docker build along docker compose up
	# User "make all"

all: build docker compose_up

build:
	mvn clean install

docker:
	docker build -t springdemo .

compose_up:
	docker compose down
	docker compose up

compose_down:
	docker compose down

