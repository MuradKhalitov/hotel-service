# Бронирование отелей

## Описание проекта
Сервис для администрирования и бронирования отелей

## Стэк используемых технологий
* Java
* Maven
* Postgresql
* MongoDB
* Kafka
* Zookeeper

## Инструкция
### Конфигурация проекта 
[Пример файла](src/main/resources/application.yaml)
#### Основные настройки
* spring.kafka.bootstrap-servers - адрес брокера Kafka
* spring.data.mongodb.uri - адрес сервера MongoDB
* spring.datasource.username - Пользователь базы данных
* spring.datasource.password - Пароль базы данных
* spring.datasource.url - Адрес сервера базы данных

Остальные параметры можно оставить без изменений

### Сборка и запуск приложения
После того как приложение было сконфигурировано, его нужно собрать.
Для этого введите в терминале команду ниже:
```shell
mvn clean package
```
После завершение сборки можно запустить проект.
Для запуска проекта внутри docker, подготовлен docker-compose.yaml и docker-start.cmd файлы:
переходим в папку docker командой:
```cmd
cd docker
```
запускаем docker-start.cmd:
```cmd
docker-start.cmd
```
