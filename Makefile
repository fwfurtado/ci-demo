

test:
	@ docker container run -d --rm --name postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=ci_test -p 5432:5432 postgres
	@ sleep 3
	@ ./mvnw clean test

package:
	@ ./mvnw package -DskipTests

deploy/contract:
	@ ./mvnw install -DskipTests