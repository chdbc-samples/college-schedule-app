# Система управління розкладом занять

Веб-додаток на Spring Boot для управління розкладом занять та реєстрації студентів на курси.

## Необхідні компоненти

- Java 17
- Maven
- PostgreSQL

## Налаштування

1. Створіть базу даних PostgreSQL з назвою `college_db`
2. Встановіть пароль бази даних як змінну середовища (команда для ОС Windows):
   ```
   $env:DB_PASSWORD="your_password"
   ```
3. Зберіть проект:
   ```
   mvn clean install
   ```
4. Згенеруйте звіти з результатами виконання перевірок:
   ```
   mvn site
   ```

Всі звіти будуть доступні в директорії `target/site`.


## Запуск додатку

```bash
mvn spring-boot:run
```

Додаток буде доступний за адресою `http://localhost:8080`

## Команди для розробки

### Тестування

Запуск лише модульних тестів:
```bash
mvn test
```

Запуск лише інтеграційних тестів:
```bash
mvn verify -DskipUnitTests
```

Запуск всіх тестів (модульних + інтеграційних):
```bash
mvn verify
```

Запуск лише тестів, що не пройшли:
```bash
mvn test -Dtest=@rerun.txt
```

# Кроки для запуску програми і відкриття web-interface
1. Встановіть локально PostgreSQL, Maven та Java.
2. Створіть базу даних college_db.
3. Встановіть змінну середовища `DB_PASSWORD` з паролем для бази даних PostgreSQL: `$env:DB_PASSWORD = "db-password"`
4. Для Windows налаштуйте консоль на використання UTF-8 кодування: `chcp 65001`
5. Зберіть програму за допомогою команди: `mvn clean install`.
6. Запустіть програму за допомогою команди: `mvn exec:java -"Dexec.mainClass=com.college.MainApp"`.
7. Відкрийте web-interface в браузері за адресою localhost:8080

## Використання змінних середовища для паролів бази даних

Програма тепер використовує змінні середовища для отримання пароля бази даних, замість зберігання його у файлі конфігурації. Це підвищує безпеку та відповідає передовим практикам розробки програмного забезпечення.

### Локальне середовище

Для локального середовища, встановіть змінну середовища `DB_PASSWORD` перед запуском програми:

**Windows:**
```
$env:DB_PASSWORD = "your_password"
```

**Linux/macOS:**
```
export DB_PASSWORD=your_password
```

### Запуск з UTF-8 кодуванням

Для правильного відображення українських символів у консолі Windows використовуйте наступні команди:

```
chcp 65001
mvn exec:java -D"exec.mainClass=com.college.MainApp"
```
