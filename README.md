# Приклад Java-програми ведення розкладу коледжу з використанням СКБД MongoDB

## Кроки для виконання
1. Завантажте і встановіть Java Development Kit 23 for Windows.
1. Завантажте Maven з https://dlcdn.apache.org/maven/maven-3/3.9.9/binaries/apache-maven-3.9.9-bin.zip і розпакуйте його на локальний комп'ютер.
1. В Windows в параметрах системи додайте системну змінну MAVEN_HOME=<шлях до папки з Maven>
1. В Windows в параметрах системи додайте `;<шлях до папки з Maven>\bin` в системну змінну PATH.
1. Встановіть СКБД MongoDB Community Server вибрати варіант установки Complete -> "Run service as Network Service user"
1. Встановіть MongoDB Compass (GUI).
1. Створіть базу даних `college-db` і колекцію в ній `college-schedule`.
1. Зберить програму використовуючи команду `mvn clean install`.
1. Запустіть програму за допомогою команди `mvn exec:java -Dexec.mainClass="com.college.CollegeApplication"`.

## Результати виконання програми
```
PS C:\Users\dmitr\OneDrive\Documents\GitHub\college-schedule-app> mvn clean install
[INFO] Scanning for projects...
[INFO] 
[INFO] --------------------< com.college:college-schedule >--------------------
[INFO] Building college-sample 1.0-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- clean:3.2.0:clean (default-clean) @ college-schedule ---
[INFO] Deleting C:\Users\dmitr\OneDrive\Documents\GitHub\college-schedule-app\target
[INFO] 
[INFO] --- resources:3.3.1:resources (default-resources) @ college-schedule ---
[WARNING] Using platform encoding (UTF-8 actually) to copy filtered resources, i.e. build is platform dependent!
[INFO] Copying 2 resources from src\main\resources to target\classes
[INFO] 
[INFO] --- compiler:3.13.0:compile (default-compile) @ college-schedule ---
[INFO] Recompiling the module because of changed source code.
[WARNING] File encoding has not been set, using platform encoding UTF-8, i.e. build is platform dependent!
[INFO] Compiling 3 source files with javac [debug target 1.8] to target\classes
[WARNING] bootstrap class path is not set in conjunction with -source 8
  not setting the bootstrap class path may lead to class files that cannot run on JDK 8
    --release 8 is recommended instead of -source 8 -target 1.8 because it sets the bootstrap class path automatically
[WARNING] source value 8 is obsolete and will be removed in a future release
[WARNING] target value 8 is obsolete and will be removed in a future release
[WARNING] To suppress warnings about obsolete options, use -Xlint:-options.
[INFO]
[INFO] --- resources:3.3.1:testResources (default-testResources) @ college-schedule ---
[WARNING] Using platform encoding (UTF-8 actually) to copy filtered resources, i.e. build is platform dependent!
[INFO] skip non existing resourceDirectory C:\Users\dmitr\OneDrive\Documents\GitHub\college-schedule-app\src\test\resources
[INFO] 
[INFO] --- compiler:3.13.0:testCompile (default-testCompile) @ college-schedule ---
[INFO] Recompiling the module because of changed dependency.
[WARNING] File encoding has not been set, using platform encoding UTF-8, i.e. build is platform dependent!
[INFO] Compiling 1 source file with javac [debug target 1.8] to target\test-classes
[WARNING] bootstrap class path is not set in conjunction with -source 8
  not setting the bootstrap class path may lead to class files that cannot run on JDK 8
    --release 8 is recommended instead of -source 8 -target 1.8 because it sets the bootstrap class path automatically
[WARNING] source value 8 is obsolete and will be removed in a future release
[WARNING] target value 8 is obsolete and will be removed in a future release
[INFO]
[INFO] --- surefire:3.2.5:test (default-test) @ college-schedule ---
[INFO] Using auto detected provider org.apache.maven.surefire.junit.JUnit3Provider
[INFO]
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.college.AppTest
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.114 s -- in com.college.AppTest
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO]
[INFO] --- jar:3.4.1:jar (default-jar) @ college-schedule ---
[INFO] Building jar: C:\Users\dmitr\OneDrive\Documents\GitHub\college-schedule-app\target\college-schedule-1.0-SNAPSHOT.jar
[INFO]
[INFO] --- install:3.1.2:install (default-install) @ college-schedule ---
[INFO] Installing C:\Users\dmitr\OneDrive\Documents\GitHub\college-schedule-app\pom.xml to C:\Users\dmitr\.m2\repository\com\college\college-schedule\1.0-SNAPSHOT\college-schedule-1.0-SNAPSHOT.pom
[INFO] Installing C:\Users\dmitr\OneDrive\Documents\GitHub\college-schedule-app\target\college-schedule-1.0-SNAPSHOT.jar to C:\Users\dmitr\.m2\repository\com\college\college-schedule\1.0-SNAPSHOT\college-schedule-1.0-SNAPSHOT.jar
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  14.922 s
[INFO] Finished at: 2024-11-20T22:01:26+02:00
[INFO] ------------------------------------------------------------------------
PS C:\Users\dmitr\OneDrive\Documents\GitHub\college-schedule-app> mvn exec:java -D"exec.mainClass=com.college.CollegeApplication"
[INFO] Scanning for projects...
[INFO] 
[INFO] --------------------< com.college:college-schedule >--------------------
[INFO] Building college-sample 1.0-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- exec:3.5.0:java (default-cli) @ college-schedule ---

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.5.4)

2024-11-20 22:01:50.627  INFO 5144 --- [lication.main()] com.college.CollegeApplication           : Starting CollegeApplication using Java 23 on DESKTOP-11MK3R9 with PID 5144 (C:\Users\dmitr\OneDrive\Documents\GitHub\college-schedule-app\target\classes started by dmitr in C:\Users\dmitr\OneDrive\Documents\GitHub\college-schedule-app)
2024-11-20 22:01:50.633  INFO 5144 --- [lication.main()] com.college.CollegeApplication           : No active profile set, falling back to default profiles: default
2024-11-20 22:01:51.232  INFO 5144 --- [lication.main()] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data MongoDB repositories in DEFAULT mode.
2024-11-20 22:01:51.317  INFO 5144 --- [lication.main()] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 78 ms. Found 1 MongoDB repository interfaces.
2024-11-20 22:01:51.774  INFO 5144 --- [lication.main()] org.mongodb.driver.cluster               : Cluster created with settings {hosts=[localhost:27017], mode=SINGLE, requiredClusterType=UNKNOWN, serverSelectionTimeout='30000 ms'}
2024-11-20 22:01:51.878  INFO 5144 --- [localhost:27017] org.mongodb.driver.connection            : Opened connection [connectionId{localValue:1, serverValue:19}] to localhost:27017
2024-11-20 22:01:51.878  INFO 5144 --- [localhost:27017] org.mongodb.driver.connection            : Opened connection [connectionId{localValue:2, serverValue:20}] to localhost:27017
2024-11-20 22:01:51.879  INFO 5144 --- [localhost:27017] org.mongodb.driver.cluster               : Monitor thread successfully connected to server with description ServerDescription{address=localhost:27017, type=STANDALONE, state=CONNECTED, ok=true, minWireVersion=0, maxWireVersion=25, maxDocumentSize=16777216, logicalSessionTimeoutMinutes=30, roundTripTimeNanos=29629300}
2024-11-20 22:01:52.437  INFO 5144 --- [lication.main()] com.college.CollegeApplication           : Started CollegeApplication in 2.435 seconds (JVM running for 6.497)
1. Додати розклад з CSV-файлу
2. Подивитись розклад
3. Видалити розклад
4. Вихід
Введіть номер команди (1-4): 1
2024-11-20 22:01:55.904  INFO 5144 --- [lication.main()] org.mongodb.driver.connection            : Opened connection [connectionId{localValue:3, serverValue:21}] to localhost:27017
5 документів з рядками з розкладу завантажено з CSV.
1. Додати розклад з CSV-файлу
2. Подивитись розклад
3. Видалити розклад
4. Вихід
Введіть номер команди (1-4): 2
Знайдено 5 документів розкладу:
Schedule { id="673e40339940345e9a4f1b4b"
 studentFirstName="Аліса"
 studentLastName="Мельник"
 teacherFirstName="Іван"
 teacherLastName="Петренко"
 courseName="Вступ до програмування"
 departmentName="Комп`ютерні науки"
 roomNumber="210"
 semester="Осінь"
 year="2024"
 startTime="09:00:00"
 endTime="10:30:00"
}
Schedule { id="673e40339940345e9a4f1b4c"
 studentFirstName="Катерина"
 studentLastName="Левченко"
 teacherFirstName="Іван"
 teacherLastName="Петренко"
 courseName="Вступ до програмування"
 departmentName="Комп`ютерні науки"
 roomNumber="210"
 semester="Осінь"
 year="2024"
 startTime="09:00:00"
 endTime="10:30:00"
}
Schedule { id="673e40339940345e9a4f1b4d"
 studentFirstName="Дмитро"
 studentLastName="Шевченко"
 teacherFirstName="Іван"
 teacherLastName="Петренко"
 courseName="Вступ до програмування"
 departmentName="Комп`ютерні науки"
 roomNumber="210"
 semester="Осінь"
 year="2024"
 startTime="09:00:00"
 endTime="10:30:00"
}
Schedule { id="673e40339940345e9a4f1b4e"
 studentFirstName="Богдан"
 studentLastName="Іванов"
 teacherFirstName="Оксана"
 teacherLastName="Коваль"
 courseName="Математичний аналіз I"
 departmentName="Математика"
 roomNumber="212"
 semester="Осінь"
 year="2024"
 startTime="11:00:00"
 endTime="12:30:00"
}
Schedule { id="673e40339940345e9a4f1b4f"
 studentFirstName="Олена"
 studentLastName="Петренко"
 teacherFirstName="Оксана"
 teacherLastName="Коваль"
 courseName="Математичний аналіз I"
 departmentName="Математика"
 roomNumber="212"
 semester="Осінь"
 year="2024"
 startTime="11:00:00"
 endTime="12:30:00"
}
1. Додати розклад з CSV-файлу
2. Подивитись розклад
3. Видалити розклад
4. Вихід
Введіть номер команди (1-4): 3
Розклад видалено.
1. Додати розклад з CSV-файлу
2. Подивитись розклад
3. Видалити розклад
4. Вихід
Введіть номер команди (1-4): 1
5 документів з рядками з розкладу завантажено з CSV.
1. Додати розклад з CSV-файлу
2. Подивитись розклад
3. Видалити розклад
4. Вихід
Введіть номер команди (1-4): 4
2024-11-20 22:02:07.334  INFO 5144 --- [ionShutdownHook] org.mongodb.driver.connection            : Closed connection [connectionId{localValue:3, serverValue:21}] to localhost:27017 because the pool has been closed.'''