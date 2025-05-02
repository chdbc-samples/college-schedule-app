# Кроки для виконання
1. Встановіть локально PostgreSQL, Maven та Java.
1. Створіть базу даних college_db.
1. Вкажіть правильне значення властивості `hibernate.connection.password` у файлі "src\main\resources\hibernate.cfg.xml".
1. Зберіть програму за допомогою команди: `mvn clean install`.
1. Запустіть програму за допомогою команди: `mvn exec:java -D"exec.mainClass=com.college.MainApp"`.

# Результати виконання програми 
```
Nov 06, 2024 2:43:34 AM org.hibernate.Version logVersion
INFO: HHH000412: Hibernate ORM core version 5.6.14.Final
Nov 06, 2024 2:43:35 AM org.hibernate.boot.jaxb.internal.stax.LocalXmlResourceResolver resolveEntity
WARN: HHH90000012: Recognized obsolete hibernate namespace http://hibernate.sourceforge.net/hibernate-configuration. Use namespace http://www.hibernate.org/dtd/hibernate-configuration instead.  Support for obsolete DTD/XSD namespaces may be removed at any time.
Nov 06, 2024 2:43:35 AM org.hibernate.annotations.common.reflection.java.JavaReflectionManager <clinit>
INFO: HCANN000001: Hibernate Commons Annotations {5.1.2.Final}
Nov 06, 2024 2:43:36 AM org.hibernate.engine.jdbc.connections.internal.ConnectionProviderInitiator instantiateC3p0Provider
WARN: HHH000022: c3p0 properties were encountered, but the c3p0 provider class was not found on the classpath; these properties are going to be ignored.
Nov 06, 2024 2:43:36 AM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl configure
WARN: HHH10001002: Using Hibernate built-in connection pool (not for production use!)
Nov 06, 2024 2:43:36 AM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001005: using driver [org.postgresql.Driver] at URL [jdbc:postgresql://localhost:5432/college_db]
Nov 06, 2024 2:43:36 AM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001001: Connection properties: {password=****, user=postgres}
Nov 06, 2024 2:43:36 AM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001003: Autocommit mode: false
Nov 06, 2024 2:43:36 AM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl$PooledConnections <init>
INFO: HHH000115: Hibernate connection pool size: 20 (min=1)
Nov 06, 2024 2:43:36 AM org.hibernate.dialect.Dialect <init>
INFO: HHH000400: Using dialect: org.hibernate.dialect.PostgreSQLDialect
Hibernate: 

    alter table class_schedules
       drop constraint FKme1t7iugw17i8ye64yafbkpno
Nov 06, 2024 2:43:38 AM org.hibernate.resource.transaction.backend.jdbc.internal.DdlTransactionIsolatorNonJtaImpl getIsolatedConnection
INFO: HHH10001501: Connection obtained from JdbcConnectionAccess [org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator$ConnectionProviderJdbcConnectionAccess@6e71cdf9] 
for (non-JTA) DDL execution was not in auto-commit mode; the Connection 'local transaction' will be committed and the Connection will be set into auto-commit mode.
Hibernate: 

    alter table class_schedules
       drop constraint FK2rlbqylwb07utwfushcbsv2an
Hibernate:

    alter table class_schedules
       drop constraint FKjrtiswbp5lypa0jqhwtbfnlxc
Hibernate:

    alter table courses
       drop constraint FKsv2mdywju86wq12x4did4xd78
Hibernate:

    alter table enrollments
       drop constraint FKho8mcicp4196ebpltdn9wl6co
Hibernate: 

    alter table enrollments
       drop constraint FK8kf1u1857xgo56xbfmnif2c51
Hibernate:

    alter table students
       drop constraint FKalgc33nsolpmegw14o3h6g6rr
Hibernate:

    alter table teachers
       drop constraint FKrgr03njnvpwuktc0mntf8t6o0
Hibernate:

    drop table if exists class_schedules cascade
Hibernate: 

    drop table if exists courses cascade
Hibernate:

    drop table if exists departments cascade
Hibernate: 

    drop table if exists enrollments cascade
Hibernate: 

    drop table if exists rooms cascade
Hibernate:

    drop table if exists students cascade
Hibernate: 

    drop table if exists teachers cascade
Hibernate: 

    create table class_schedules (
       schedule_id  serial not null,
        end_time time,
        semester varchar(255),
        start_time time,
        year int4 not null,
        course_id int4,
        room_id int4,
        teacher_id int4,
        primary key (schedule_id)
    )
Nov 06, 2024 2:43:38 AM org.hibernate.resource.transaction.backend.jdbc.internal.DdlTransactionIsolatorNonJtaImpl getIsolatedConnection
INFO: HHH10001501: Connection obtained from JdbcConnectionAccess [org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator$ConnectionProviderJdbcConnectionAccess@20374bf] for (non-JTA) DDL execution was not in auto-commit mode; the Connection 'local transaction' will be committed and the Connection will be set into auto-commit mode.
Hibernate: 

    create table courses (
       course_id  serial not null,
        course_name varchar(255),
        credits int4 not null,
        department_id int4,
        primary key (course_id)
    )
Hibernate: 

    create table departments (
       department_id  serial not null,
        location varchar(255),
        name varchar(255),
        primary key (department_id)
    )
Hibernate: 

    create table enrollments (
       enrollment_id  serial not null,
        enrollment_date date,
        course_id int4,
        student_id int4,
        primary key (enrollment_id)
    )
Hibernate: 

    create table rooms (
       room_id  serial not null,
        capacity int4,
        room_number varchar(255),
        primary key (room_id)
    )
Hibernate: 

    create table students (
       student_id  serial not null,
        first_name varchar(255),
        last_name varchar(255),
        department_id int4,
        primary key (student_id)
    )
Hibernate: 

    create table teachers (
       teacher_id  serial not null,
        first_name varchar(255),
        last_name varchar(255),
        department_id int4,
        primary key (teacher_id)
    )
Hibernate: 

    alter table class_schedules
       add constraint FKme1t7iugw17i8ye64yafbkpno
       foreign key (course_id)
       references courses
Hibernate: 

    alter table class_schedules
       add constraint FK2rlbqylwb07utwfushcbsv2an
       foreign key (room_id)
       references rooms
Hibernate:

    alter table class_schedules
       add constraint FKjrtiswbp5lypa0jqhwtbfnlxc
       foreign key (teacher_id)
       references teachers
Hibernate:

    alter table courses
       add constraint FKsv2mdywju86wq12x4did4xd78
       foreign key (department_id)
       references departments
Hibernate:

    alter table enrollments
       add constraint FKho8mcicp4196ebpltdn9wl6co
       foreign key (course_id)
       references courses
Hibernate:

    alter table enrollments
       add constraint FK8kf1u1857xgo56xbfmnif2c51
       foreign key (student_id)
       references students
Hibernate:

    alter table students
       add constraint FKalgc33nsolpmegw14o3h6g6rr
       foreign key (department_id)
       references departments
Hibernate:

    alter table teachers
       add constraint FKrgr03njnvpwuktc0mntf8t6o0
       foreign key (department_id)
       references departments
Hibernate: 
    insert
    into
        departments
        (location, name)
    values
        (?, ?)
Hibernate: 
    insert
    into
        departments
        (location, name)
    values
        (?, ?)
Hibernate:
    insert
    into
        teachers
        (department_id, first_name, last_name)
    values
        (?, ?, ?)
Hibernate: 
    insert
    into
        teachers
        (department_id, first_name, last_name)
    values
        (?, ?, ?)
Hibernate:
    insert
    into
        students
        (department_id, first_name, last_name)
    values
        (?, ?, ?)
Hibernate: 
    insert
    into
        students
        (department_id, first_name, last_name)
    values
        (?, ?, ?)
Hibernate:
    insert
    into
        students
        (department_id, first_name, last_name)
    values
        (?, ?, ?)
Hibernate:
    insert
    into
        students
        (department_id, first_name, last_name)
    values
        (?, ?, ?)
Hibernate: 
    insert
    into
        students
        (department_id, first_name, last_name)
    values
        (?, ?, ?)
Hibernate:
    insert
    into
        courses
        (course_name, credits, department_id)
    values
        (?, ?, ?)
Hibernate:
    insert
    into
        courses
        (course_name, credits, department_id)
    values
        (?, ?, ?)
Hibernate:
    insert
    into
        rooms
        (capacity, room_number)
    values
        (?, ?)
Hibernate:
    insert
    into
        rooms
        (capacity, room_number)
    values
        (?, ?)
Hibernate:
    insert
    into
        class_schedules
        (course_id, end_time, room_id, semester, start_time, teacher_id, year)
    values
        (?, ?, ?, ?, ?, ?, ?)
Hibernate:
    insert
    into
        class_schedules
        (course_id, end_time, room_id, semester, start_time, teacher_id, year)
    values
        (?, ?, ?, ?, ?, ?, ?)
Hibernate:
    insert
    into
        enrollments
        (course_id, enrollment_date, student_id)
    values
        (?, ?, ?)
Hibernate:
    insert
    into
        enrollments
        (course_id, enrollment_date, student_id)
    values
        (?, ?, ?)
Hibernate:
    insert
    into
        enrollments
        (course_id, enrollment_date, student_id)
    values
        (?, ?, ?)
Hibernate:
    insert
    into
        enrollments
        (course_id, enrollment_date, student_id)
    values
        (?, ?, ?)
Hibernate:
    insert
    into
        enrollments
        (course_id, enrollment_date, student_id)
    values
        (?, ?, ?)
Дан? усп?шно збережено!
Hibernate: 
    select
        student1_.first_name as col_0_0_,
        student1_.last_name as col_1_0_,
        teacher4_.first_name as col_2_0_,
        teacher4_.last_name as col_3_0_,
        course2_.course_name as col_4_0_,
        department5_.name as col_5_0_,
        room6_.room_number as col_6_0_,
        classsched3_.semester as col_7_0_,
        classsched3_.year as col_8_0_,
        classsched3_.start_time as col_9_0_,
        classsched3_.end_time as col_10_0_
    from
        enrollments enrollment0_
    inner join
        students student1_
            on enrollment0_.student_id=student1_.student_id
    inner join
        courses course2_
            on enrollment0_.course_id=course2_.course_id
    inner join
        class_schedules classsched3_
            on course2_.course_id=classsched3_.course_id
    inner join
        teachers teacher4_
            on classsched3_.teacher_id=teacher4_.teacher_id
    inner join
        departments department5_
            on course2_.department_id=department5_.department_id
    inner join
        rooms room6_
            on classsched3_.room_id=room6_.room_id
Студент: Ал?са Мельник
Викладач: ?ван Петренко
Курс: Вступ до програмування
Кафедра: Комп`ютерн? науки
Аудитор?я: 210
Семестр: Ос?нь
Р?к: 2024
Час початку: 09:00
Час зак?нчення: 10:30
--------------------------------------------------
Студент: Катерина Левченко
Викладач: ?ван Петренко
Курс: Вступ до програмування
Кафедра: Комп`ютерн? науки
Аудитор?я: 210
Семестр: Ос?нь
Р?к: 2024
Час початку: 09:00
Час зак?нчення: 10:30
--------------------------------------------------
Студент: Дмитро Шевченко
Викладач: ?ван Петренко
Курс: Вступ до програмування
Кафедра: Комп`ютерн? науки
Аудитор?я: 210
Семестр: Ос?нь
Р?к: 2024
Час початку: 09:00
Час зак?нчення: 10:30
--------------------------------------------------
Студент: Богдан ?ванов
Викладач: Оксана Коваль
Курс: Математичний анал?з I
Кафедра: Математика
Аудитор?я: 212
Семестр: Ос?нь
Р?к: 2024
Час початку: 11:00
Час зак?нчення: 12:30
--------------------------------------------------
Студент: Олена Петренко
Викладач: Оксана Коваль
Курс: Математичний анал?з I
Кафедра: Математика
Аудитор?я: 212
Семестр: Ос?нь
Р?к: 2024
Час початку: 11:00
Час зак?нчення: 12:30
--------------------------------------------------
Nov 06, 2024 2:43:39 AM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl$PoolState stop
INFO: HHH10001008: Cleaning up connection pool [jdbc:postgresql://localhost:5432/college_db]
```
