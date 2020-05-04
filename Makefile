

test:
	@ docker container run -d --rm --name postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=ci_test -p 5432:5432 postgres
	@ sleep 3
	@ ./mvnw clean test

package:
	@ ./mvnw package -DskipTests

deploy/contract:
	@ ./mvnw install -DskipTests

deploy: _build-docker-image _login-with-registry
	@ docker tag ci-demo  registry.heroku.com/ci-demo-deploy/web:$$TRAVIS_BUILD_ID
	@ docker image push registry.heroku.com/ci-demo-deploy/web:$$TRAVIS_BUILD_ID
	@ make _deploy-on-heroku IMAGE_ID=$$(docker image inspect registry.heroku.com/ci-demo-deploy/web:$$TRAVIS_BUILD_ID -f {{.Id}} )

_deploy-on-heroku:
	@ curl -X PATCH \
			-H "Authorization: Bearer $$DOCKER_REGISTRY_TOKEN" \
			-H "Content-Type: application/json" \
			-H "Accept:application/vnd.heroku+json; version=3.docker-releases" \
			-d '{ "updates": [{"type": "web",  "docker_image": "$(IMAGE_ID)"}] }' \
			https://api.heroku.com/apps/ci-demo-deploy/formation

_build-docker-image: package
	@ docker image build --build-arg JAR=target/ci-demo-0.0.1-SNAPSHOT.jar -t ci-demo .

_login-with-registry:
	@ docker login --username=_ --password=$$DOCKER_REGISTRY_TOKEN registry.heroku.com