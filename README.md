##Дипломная работа професси "Тестировщик ПО" ##

Дипломный проект представляет собой автоматизацию тестирования комплексного сервиса по покупке тура с помощью дебетовой карты и выдача кредита по данным банковской карты, взаимодействующего с СУБД и API Банка.

###Документация проекта ###

* План по автоматизации тестирования
* Отчет по итогам автоматизированного тестирования
* Отчет по итогам автоматизации

####Используемое окружение: ####

* Java 11
* Installed IntelliJ IDEA
* Installed Docker
* Installed Node.js
* Браузер на выбор
* убедитесь, что порты 8080, 9999 и 5432 или 3306 (в зависимости от выбранной базы данных) свободны.

####Этапы по запуску тестов: ####

1. Склонируйте репозиторий;
   
1. Запустите контейнер, с предоствыленной базой данных (БД) `docker-compose up -d --force-recreate`

1. Убедитесь в том, что БД готова к работе (логи можно смотреть через `docker-compose logs -f <applicationName>` (mysql или postgres)

1. Запустите приложение командой :

* для использования Postgres: `java -Dspring.datasource.url=jdbc:postgresql://localhost:5432/postgres -jar artifacts/aqa-shop.jar` (`java -jar aqa-shop.jar`)
* для использования MySQL: `java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -jar artifacts/aqa-shop.jar`

Приложение запускается на порту 8080;

1. Запустите тесты командой:

* при работе с postgres: `gradlew clean test -Ddb.url=jdbc:postgresql://localhost:5432/postgres -Dlogin=app -Dpassword=pass -Dapp.url=http://localhost:8080`
* при работе с mySql: `gradlew clean test -Ddb.url=jdbc:mysql://localhost:3306/app -Dlogin=app -Dpassword=pass -Dapp.url=http://localhost:8080 `.