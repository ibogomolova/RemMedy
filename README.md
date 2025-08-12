# RemMedy — медицинское приложение-напоминалка

**Кратко:** RemMedy (pharma-box) — Spring Boot приложение, которое позволяет пользователям добавлять лекарства (штрихкод/фото), хранить данные о сроках годности и получать уведомления (email / push) когда срок годности приближается или истёк.

---

## Содержание README
1. Описание
2. Фичи
3. Архитектура и стек
4. Быстрый старт (локально)
5. Переменные окружения / конфигурация
6. Docker / docker-compose (еще нет)
7. База данных и миграции
8. Тестирование и локальная проверка email
9. Эндпоинты (основные)
10. Рекомендации по безопасности и продакшену
11. Развитие проекта / contribution
12. Контакты / лицензия

---

## 1. Описание
RemMedy — backend часть (Spring Boot) для приложения-напоминалки о лекарствах. Приложение хранит информацию о лекарствах пользователя, настройках уведомлений и создаёт напоминания по расписанию (scheduler).

## 2. Фичи
- Регистрация и логин пользователей (DTO для логина/регистрации в проекте)
- CRUD для лекарств (название, дата добавления, срок годности, изображение, штрихкод)
- Настройки уведомлений на пользователя (email/push, дни до срока)
- Планировщик, который периодически проверяет лекарства и рассылает уведомления
- REST API + OpenAPI (Swagger)
- Liquibase миграции для БД
- Простой MailService (JavaMailSender) для отправки email

## 3. Архитектура и стек
- Java 17+
- Spring Boot (Web, Data JPA)
- Spring Scheduling (@Scheduled)
- PostgreSQL
- Liquibase (миграции)
- JavaMailSender (SMTP)
- Lombok
- MapStruct
- JUnit5, Mockito (скоро будут!)
- Docker (тоже будет)

Структура пакетов (основные):

com.remmedy.pharma_box
├─ controller
├─ dto
├─ entity
├─ exception
├─ mapper
├─ repository
└─ service / service.impl

## 4. Быстрый старт (локально)
### Требования
- Java 17
- Maven 3.8+
- PostgreSQL
- Docker, docker-compose (будет)

### Сборка и запуск
1. Клонируй репозиторий и перейди в папку проекта:
bash
git clone <https://github.com/ibogomolova/RemMedy.git>
cd pharma-box

Настрой переменные окружения (см. раздел 5). 
Альтернативно отредактируй src/main/resources/application.properties (не храни секреты в репозитории!).

Запусти PostgreSQL локально и создай БД (или используй docker-compose, пример ниже).

Собери проект и запусти:

./mvnw clean package -DskipTests
./mvnw spring-boot:run


Проверь Swagger UI: http://localhost:8080/swagger-ui/index.html (если включён) или /v3/api-docs для JSON схемы.

5. Переменные окружения / конфигурация
Пример application.properties (замени значения на реальные через env vars):

spring.datasource.url=jdbc:postgresql://localhost:5432/${REMMEDY_DB}
spring.datasource.username=${REMMEDY_USER}
spring.datasource.password=${REMMEDY_PASSWORD}
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# SMTP
spring.mail.host=smtp.mail.ru
spring.mail.port=465
spring.mail.username=noreply@remmedy.site
spring.mail.password=${REMMEDY_SMTP_PASS}
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.ssl.enable=true

# Liquibase changelog
liquibase.change-log=classpath:db/changelog-master.yaml

Совет: храните пароли и секреты в переменных окружения или secret manager (Vault / cloud secrets).

6. Docker / docker-compose (будет :(...)


7. База данных и миграции
Проект содержит Liquibase миграции в src/main/resources/db/. При запуске приложение ожидает, что схема совпадает с миграциями (ddl-auto=validate).

Чтобы применить миграции вручную (если нужно), можно использовать Liquibase CLI или позволить Spring Boot выполнить их при старте.

8. Тестирование и локальная проверка email
Запуск тестов:
```./mvnw test```

Простой endpoint для проверки SMTP уже есть:
GET /test-email — отправляет тестовое письмо на адрес, указанный в коде (в TestMailController). Отредактируй адрес или используй контроллер как шаблон для тестирования.

Отладка почты: включи spring.mail.properties.mail.debug=true в properties, чтобы видеть SMTP-диалог в логах.

9. Основные эндпоинты (быстрый список)
(Автоматически извлечены из контроллеров — проверить в коде для деталей запросов/тел)

POST /medicines — создание лекарства

GET /medicines/user/{userId} — список лекарств пользователя

PUT /medicines/{id} — обновление лекарства

DELETE /medicines/{id} — удаление лекарства

POST /notification-settings — создать/обновить настройки уведомлений

GET /reminders/user/{userId} — список напоминаний пользователя

GET /test-email — отправка тестового письма

POST /users/register — регистрация (если контроллер реализован)

POST /users/login — логин (если реализован)

(Подробные схемы DTO находятся в src/main/java/.../dto.)

10. Рекомендации по безопасности

Реализуй/проверь Spring Security + JWT. 

Не хранить userId из внешнего запроса без проверки — брать идентификатор из токена.

Хэшируй пароли с помощью BCryptPasswordEncoder.

Разреши доступ к Swagger эндпоинтам отдельно.

Секреты и пароли — только в защищённом хранилище (env vars / vault).

При масштабировании: scheduler работает в каждом инстансе — возможны дубль-уведомления. 
Решения: leader-election (Redis lock, DB lock) либо внешняя очередь / Cron в k8s.

11. Contribution / Development
Форкни репозиторий и создай фичу/ветку.

Пиши тесты для новых функций (unit + интеграционные).

Открывай Pull Request с описанием изменений и шагами для тестирования.

12. Troubleshooting (частые проблемы)
Swagger не загружается (500 /v3/api-docs): проверь логи при запросе /v3/api-docs — часто падает из-за сериализации Entity (LAZY relations) или ошибок в аннотациях. 
Решение: использовать DTO, @JsonIgnore, или исправить проблемный класс.

SMTP ошибки: проверь правильность пароля, используемость пароля приложения (mail.ru/Google), порт и SSL/TLS настройки.

Scheduler отправляет дубликаты: убедись, что помечаешь Reminder.sent=true и фильтруешь уже отправленные, либо сделай unique-constraint/блокировку в DB.

# Полезные команды

 Запуск приложения
./mvnw spring-boot:run

 Сборка jar
./mvnw clean package -DskipTests

 Запуск тестов
./mvnw test
