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

## Публікація артефактів

Проект налаштовано на автоматичну публікацію артефактів у GitHub Packages при кожному push в гілку main. 

### Використання пакетів

Для використання опублікованих пакетів у вашому проекті, додайте наступну конфігурацію до вашого `pom.xml`:

```xml
<dependencies>
    <dependency>
        <groupId>com.college</groupId>
        <artifactId>college-schedule</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
</dependencies>

<repositories>
    <repository>
        <id>github</id>
        <url>https://maven.pkg.github.com/OWNER/college-schedule-app</url>
    </repository>
</repositories>
```

Замініть `OWNER` на ім'я власника репозиторію.

### Налаштування автентифікації

Для доступу до пакетів вам потрібно налаштувати автентифікацію GitHub:

#### Windows
1. Створіть файл `settings.xml` в директорії `%USERPROFILE%\.m2\` (зазвичай `C:\Users\YourUsername\.m2\`)
2. Додайте наступну конфігурацію:

```xml
<settings>
  <servers>
    <server>
      <id>github</id>
      <username>YOUR_GITHUB_USERNAME</username>
      <password>YOUR_GITHUB_TOKEN</password>
    </server>
  </servers>
</settings>
```

Якщо директорія `.m2` не існує:
```cmd
mkdir "%USERPROFILE%\.m2"
```

#### Linux/macOS
Додайте конфігурацію в файл `~/.m2/settings.xml`:

```xml
<settings>
  <servers>
    <server>
      <id>github</id>
      <username>YOUR_GITHUB_USERNAME</username>
      <password>YOUR_GITHUB_TOKEN</password>
    </server>
  </servers>
</settings>
```

#### Отримання токену GitHub
1. Перейдіть в налаштування GitHub:
   - Settings -> Developer settings -> Personal access tokens
   - або використайте пряме посилання: https://github.com/settings/tokens
2. Натисніть "Generate new token"
3. Надайте токену необхідні права:
   - `read:packages` - для завантаження пакетів
   - `write:packages` - для публікації пакетів
4. Скопіюйте згенерований токен і збережіть його в безпечному місці
5. Використайте цей токен у файлі `settings.xml` замість `YOUR_GITHUB_TOKEN`

Замініть у конфігурації:
- `YOUR_GITHUB_USERNAME` на ваше ім'я користувача GitHub
- `YOUR_GITHUB_TOKEN` на токен, який ви щойно створили

## Релізи

### Створення нового релізу

Для створення нового релізу:

1. Переконайтеся, що всі необхідні зміни знаходяться в гілці `main`
2. Створіть новий тег з версією:
   ```bash
   git tag -a v1.0.0 -m "Release version 1.0.0"
   git push origin v1.0.0
   ```
3. GitHub Actions автоматично:
   - Збере проект
   - Згенерує changelog на основі комітів та PR
   - Створить новий реліз на GitHub
   - Прикріпить до релізу JAR-файли та документацію

### Формат версіонування

Ми використовуємо семантичне версіонування (MAJOR.MINOR.PATCH):
- MAJOR - несумісні зміни API
- MINOR - нові функції із зворотною сумісністю
- PATCH - виправлення багів із зворотною сумісністю

### Формат комітів

Для кращої автоматичної генерації changelog використовуйте префікси в повідомленнях комітів:
- `feat:` - нова функціональність
- `fix:` - виправлення помилок
- `docs:` - зміни в документації
- `perf:` - оптимізація продуктивності
- `refactor:` - рефакторинг коду
- `test:` - додавання тестів
- `build:` - зміни в процесі збірки
- `ci:` - зміни в CI/CD конфігурації
