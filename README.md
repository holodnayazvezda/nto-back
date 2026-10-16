# nto-back

Учебный сервер проекта НТО на Java 11 и Spring Boot 2.5.5. Предоставляет операции получения информации о сотруднике, авторизации и проверки кода. Данные хранятся через Spring Data JPA в H2.

## Запуск

Требуются JDK 11 и Maven. Из корня проекта:

```sh
mvn spring-boot:run
```

По умолчанию сервер слушает `http://localhost:3000`. Настройки находятся в `src/main/resources/application.properties`. Используется база H2 в памяти с `create-drop`, поэтому данные не сохраняются между запусками.

## API

- `GET /api/hello?name=World` — проверка ответа сервера.
- `GET /api/{username}/info` — сведения о сотруднике.
- `GET /api/{username}/auth` — авторизация.
- `PATCH /api/{username}/open` — передача кода `value`.

Контроллер и модели запросов находятся в `src/main/java/com/example/nto/`; точный формат параметров следует смотреть в `controller/EmployeeController.java` и `domain/CodeDTO.java`.
