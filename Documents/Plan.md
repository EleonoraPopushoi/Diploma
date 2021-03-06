## План по автоматизации тестирования

***Перечень автоматизируемых видов сценариев:***

*Позитивные сценарии:*

1. Пользователь вводит валидные данные, выбирая оплату по дебетовой карте, операция проходит без отказа с подтверждением об оплате; 
1. Пользователь вводит валидные данные, выбирая оплату с использованием кредита по кредитной карте, операция проходит без отказа с подтверждением об оплате;
1. Пользователь вводит валидные данные, выбирает оплату по дебетовой карте, условие: на карте при этом недостаточно средств для выполнения операции - происходит отказ в операции(по условиям для этого сценария дан номер карты по которому операция будет отклонена);
1. Пользователь вводит валидные данные, выбирая оплату по кредитной карте - происходит отказ в выполнении операции (по условиям для этого сценария дан номер карты по которому операция будет отклонена).


*Негативные сценарии:*

1. Пользователь оплачивает покупку с дебетовой карты, заполняет поля невалидными данными;
1. Пользователь оплачивает покупку с помощью кредитной карты, заполняет поля невалидными данными;

*При проверке негативных сценариев с вводом невалидных данных проверяем, что:*

* появляются подсказки о том, что поле неверно заполнено;
* приложение не передает форму с невалидными данными в платежный сервис, соответственно, платеж откланен;
* отдельно проверим каждое поле, заполнив его данными в неверном формате.
* 2 варианта неполного номера карты, методом черного ящика, а именно граничные значения - 15 знаков и 1 знак;
* неверный формат номера месяца;
* неверный формат года;
* неверный cvc код,
* ввод цифр в поле имя пользователя;
* ввод пустого поля имя пользователя.

**Инструменты для тестирования:**

1. JUnit 5 - удобный фреймворк для автотестов.
1. Selenide - так как работаем с веб-страницей и ищем появившиеся значения с помощью html и css, создание Page Objects, заполнение формы через веб-интерфейс.
1. Faker - генерация недостающих данных для тестирования.
1. RestAssured - отправка запросов симулятору банковских сервисов, проверка того, что он принимает запросы и генерирует ответы, хорошо подходит для проверки структуры ответов.
1. Docker - для развертывания и запуска приложения в контейнере. Можно одновременно запускать на одном компьютере несколько контейнеров, при этом будет потребляться меньше ресурсов, чем для виртуализации. Удобно настраивать контейнеры через Dockerfile, по которому другие члены команды могут запустить точно такой же контейнер.
1. GitHub - для хранения проекта. Позволяет откатиться назад, если вдруг после очередных изменений что-то пошло не так и не удается исправить; код легко скачать и запустить, код доступен для совместного использования и разработки, имеется система CI.
1. Allure - используем для наглядного изображения прохождения тестов и ошибок.

**Возможные риски:**

1. Техническая сложность настройки окружения. Для работы приложения нужно: две разные базы данных (MySQL, PostgreSQL), развернуть симулятор платежной системы, написанный на другом языке. Нужно разобраться с механизмом работы данного языка, как взаимодействует с нашими БД и ОС. Возможны проблемы при настройки CI (если потребуется). Поэтому в ходе настройки окружения могут возникнуть сложности, требующие дополнительных затрат по времени.
1. Комплексный характер тестирования. В ходе обучения мы тестировали, в основном, какие-то определенные функциональности, в данном случае нужно протестировать приложение комплексно, которое насыщено багами. Потребуется больше времени на дополнительные проверки и оформление баг-репортов.
1. Большая сложность приложения. Могут возникнуть сложности с поиском элементов на странице (неуникальные идентификаторы).

**Интервальная оценка с учётом рисков:**

* Настройка с учетом рисков - 3 дня.
* Разработка - 3-7 дней.
* Отчеты - 2-3 деня.

**План сдачи работ:**

* Готовность авто-тестов - в течении 7 дней после утверждения плана.
* Результаты тестов - через 1-2 дня после прогона авто-тестов. 
* Отчет по автоматизации - через 2-3 дня после результатов прогона тестов.