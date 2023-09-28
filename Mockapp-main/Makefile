
PROJECT_DIR: .
.PHONY: clean package run_docker

all: clean package run_docker


clean:
	@echo "CLEAN MOCKAPP..."
	@cd $(PROJECT_DIR) && mvn clean

package:
	@echo "PACKAGING MOCKAPP..."
	@cd $(PROJECT_DIR) mvn package

run_docker:
	@echo "RUNNING DOCKER-COMPOSE..."
	@docker-compose up -d