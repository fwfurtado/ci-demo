

test:
	@ ./mvnw clean test

package:
	@ ./mvnw package -DskipTests

deploy/contract:
	@ ./mvnw install