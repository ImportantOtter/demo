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
* В проекте присутствуют flyway миграции, при миграции данных в БД вы получите две таблицы product и discount_card 
  
![Alt text](src/main/resources/static/6.png?raw=true "Optional Title")

![Alt text](src/main/resources/static/7.png?raw=true "Optional Title")



### Результат приложения используя Postman

* localhost:8080/product/order/create

![Alt text](src/main/resources/static/1.png?raw=true "Optional Title")

* Получение чека в файл
localhost:8080/product/exampleFile

![Alt text](src/main/resources/static/2.png?raw=true "Optional Title")



* Получение чека в браузере в HTML виде
localhost:8080/product/exampleHtmlCheque

![Alt text](src/main/resources/static/3.png?raw=true "Optional Title")

* Получение чека в консоле IDEA
localhost:8080/product/exampleSimpleCheque

![Alt text](src/main/resources/static/5.png?raw=true "Optional Title")
