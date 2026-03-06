**Restful Spring Boot Project**

*Этот проект представляет собой RESTful API, разработанный с использованием Spring Boot и Gradle. 
Он предназначен для управления данными и обеспечивает удобный интерфейс для взаимодействия с сервером через HTTP-запросы.
Проект реализует стандартные CRUD-операции (создание, чтение, обновление, удаление) и демонстрирует лучшие практики построения REST API на базе Spring Boot.*
**Основные особенности**

**Простая и расширяемая архитектура:**
Использование Spring Data JPA для работы с базой данных
Поддержка различных HTTP-методов (GET, POST, PUT, DELETE)
Валидация данных и обработка ошибок
Документация OpenAPI для удобства использования

**Стэк технологий:**

Java 11+
Spring Boot
Spring Data JPA
Hibernate
База данных PostgreSQL 
Liqudbase
Lombok
OpenApi
Lombok (по желанию для сокращения кода)

**Как запустить проект**

Клонируйте репозиторий:
git clone https://github.com/BenimaruShiimon/Restfull_SpringBoot_Project.git
      
Перейдите в директорию проекта и соберите его с помощью Gradle:
./gradlew build
      
Запустите приложение командой:
./gradlew bootRun
*или запустите класс с методом main из IDE.*

По умолчанию приложение запустится на порту 8087. API будет доступен по адресу:
http://localhost:8087

**Использование API**
Примеры запросов и документация API доступны в проекте (https://github.com/BenimaruShiimon/Restfull_SpringBoot_Project/tree/main/src/main/gen/docs).
