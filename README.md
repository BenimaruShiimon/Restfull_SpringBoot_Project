## User Web Service

Небольшой проект на Spring Boot: веб‑приложение для управления пользователями с HTML‑UI и REST API, PostgreSQL, Liquibase и OpenAPI (Swagger UI).

### Стек

- **Java**: 25
- **Build**: Gradle
- **Spring Boot**: 4.0.2 (Web MVC, Data JPA, Validation, Actuator, Thymeleaf)
- **База данных**: PostgreSQL
- **Миграции**: Liquibase
- **Документация**: springdoc-openapi 3.x + Swagger UI

### Запуск

#### 1. Подготовить БД

Создай базу (по умолчанию `postgres`):

```sql
CREATE DATABASE postgres;
```

Пользователь/пароль по умолчанию: `admin` / `admin` (можно поменять через переменные окружения).

#### 2. Переменные окружения (опционально)

```bash
# Windows PowerShell
$env:DB_URL="jdbc:postgresql://localhost:5432/postgres"
$env:DB_USERNAME="admin"
$env:DB_PASSWORD="admin"
```

Если их не задавать, будут использованы эти же значения по умолчанию (заданы в `application.yml` через плейсхолдеры).

#### 3. Сборка и запуск

```bash
./gradlew clean bootRun
```

Liquibase применит миграции, приложение поднимется на порту `8087`.

### Миграции БД

Описание находится в `src/main/resources/db/changelog`:

- `master.xml` — корневой changelog
- `2026/02/16-create-table.xml` — создание таблицы `users`

Liquibase запускается автоматически при старте приложения.

### UI (Thymeleaf)

- **Список пользователей**: `http://localhost:8087/ui/users`
- **Добавление пользователя**: `http://localhost:8087/ui/users/add`

### REST API

Базовый путь: `http://localhost:8087/api/users`

- **GET `/api/users`** — получить список пользователей (`List<UserDto>`)
- **GET `/api/users/{id}`** — получить пользователя по `id`
- **POST `/api/users`** — создать пользователя  
  Тело (`application/json`):

  ```json
  {
    "name": "John",
    "email": "john@example.com",
    "birthDate": "1990-01-01",
    "phone": 79001234567
  }
  ```

- **DELETE `/api/users/{id}`** — удалить пользователя по `id`
- **PUT `/api/users/{id}`** — обновить данные пользователя

Модель `UserDto`:

```json
{
  "id": 1,
  "name": "John",
  "email": "john@example.com",
  "birthDate": "1990-01-01",
  "phone": 79001234567
}
```

### Документация OpenAPI

- **Swagger UI**: `http://localhost:8087/swagger-ui.html`
- **OpenAPI JSON**: `http://localhost:8087/v3/api-docs`

### Генерация `openapi.json` через Gradle

Плагин `org.springdoc.openapi-gradle-plugin` настроен на вывод в `build/openapi/openapi.json`.

```bash
./gradlew generateOpenApiDocs
```

Файл появится по пути:

```text
build/openapi/openapi.json
```

### Структура проекта (основное)

- `src/main/java/com/github/benimarushiimon/webservise`
  - `model/User.java` — JPA‑сущность
  - `web/dto/UserDto.java` — DTO
  - `web/mapper/UserMapper.java` — маппер User ↔ UserDto
  - `repository/UserRepository.java` — Spring Data JPA репозиторий
  - `service/UserService.java` — бизнес‑логика
  - `service/ValidationService.java` — валидация
  - `controller/UserController.java` — HTML UI (Thymeleaf)
  - `controller/UserRestController.java` — REST API
  - `controller/OpenApiConf.java` — конфиг OpenAPI
  - `handler/GlobalExceptionHandler.java` — обработка ошибок
- `src/main/resources/templates` — HTML‑шаблоны (`user.html`, `add-form.html`)
- `src/main/resources/db/changelog` — миграции Liquibase
- `src/main/resources/application.yml` — конфигурация приложения
