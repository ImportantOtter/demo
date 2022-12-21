# Описание проекта
Приложение, реализующее функционал формирования
чека в магазине.

## Для работы

### Зависимости

* Java 17, PostgreSQL, Gradle, Postman
* Windows 10

### Как запустить

* Зайти в application.properties и поменять креды к БД(Название бд, логин и пароль)
* Запустить через идею мейн класс 

### Результат приложения используя Postman

* localhost:8080/product/order/create

![Alt text](src/main/resources/static/1.png?raw=true "Optional Title")

* Получение чека в файл
localhost:8080/product/exampleFile


* Получение чека в браузере в HTML виде
localhost:8080/product/exampleHtmlCheque

![Alt text](src/main/resources/static/3.png?raw=true "Optional Title")
