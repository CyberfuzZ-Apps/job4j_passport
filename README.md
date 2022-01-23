# Job4j_passport

## О проекте:

Job4j_passport - сервис для работы с паспортами.
Приложение реализует архитектуру REST API.

Использованы технологии:
- Spring Boot 2:
  - WebMVC
  - Data JPA / Hibernate
  - Tomcat
- Java 11
- PostgreSQL
- Maven
- Docker
- Docker-compose
- Kubernetes


## Подробнее:

### http://sitename/api/passport

Поддерживаются следующие методы:

- /save, сохранить данные паспорта
- /update?id=*, обновить данные паспорта
- /delete?id=*, удалить данные паспорта
- /find, загрузить все паспорта
- /find?seria=*, загрузить паспорта с заданной серией
- /unavailable, загрузить паспорта чей срок вышел
- /find-replaceable, загрузить паспорта, 
которые нужно заменить в ближайшие 3 месяца

## Сборка и запуск проекта:

### Maven:

Сборка с помощью Maven `mvn install`

### Docker:

Сборка с помощью Docker `docker build -t passport .`

Запуск с помощью Docker-compose `docker-compose up`

### Kubernetes:

Сборка:

- `kubectl apply -f k8s/passport-postgresdb-secret.yml`
- `kubectl apply -f k8s/passport-postgresdb-configmap.yml`
- `kubectl apply -f k8s/passport-postgresdb-deployment.yml`
- `kubectl apply -f k8s/passport-spring-boot-deployment.yml`

Запуск:

- `minikube service passport-spring-boot-service`

## Контакты.
Если у вас есть какие-либо вопросы, не стесняйтесь обращаться ко мне:

Евгений Зайцев

[cyberfuzzapps@gmail.com](mailto:cyberfuzzapps@gmail.com)
