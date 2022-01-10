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


## Подробнее:

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

Сборка с помощью Maven `mvn install`

Сборка с помощью Docker `docker build -t passport .`

Запуск с помощью Docker-compose `docker-compose up`

## Контакты.
Если у вас есть какие-либо вопросы, не стесняйтесь обращаться ко мне:

Евгений Зайцев

[cyberfuzzapps@gmail.com](mailto:cyberfuzzapps@gmail.com)
