local-run:
	mvn clean spring-boot:run

docker-run:
	docker-compose down \
	docker-compose up \
	mvn clean spring-boot:run

test:
	mvn clean test

sonar:
	mvn clean test verify sonar:sonar
