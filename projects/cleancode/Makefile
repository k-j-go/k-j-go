clean:
	docker stop $$(docker ps -aq) || true
	docker rm $$(docker ps -aq) || true
	docker rmi cleancode_cleancode  || true
	docker rmi cleancode_cleancode  || true
	docker rmi $$(docker images -f "dangling=true" -q ) || true
build: clean
	mvn install
	docker-compose build
start:
	docker-compose up -d
