# Пример использования Remote Config SDK от RuStore на kotlin

## [Документация по Remote Config SDK](https://help.rustore.ru/rustore/for_developers/tools/remote_config/sdk)

SDK Remote Config — это облачный сервис, который позволяет изменять поведение и внешний вид вашего приложения, не требуя от пользователей загрузки обновления приложения. SDK инкапсулирует в себе запрос конфигурации с сервера, кэширование, фоновое обновление. Имеет удобный api для получения данных.

Ключевые особенности:

- SDK позволяет выбрать наиболее удобный механизм обновления конфигурации.
- Имеет возможность указывать процент распространения конфигурации на аудиторию.
- Для построения воронки конкретной конфигурации можно передавать дополнительную информацию. Возможно формировать конфигурации даже для конкретных пользователей.
- Имеет набор колбэков о работе SDK, которые можно использовать для аналитики.
- Имеет минимальное количество внешних зависимостей.
- Подключение в проект

Подключите репозиторий `build.gradle`:

```
repositories {
    maven {
        url = uri("https://artifactory-external.vkpartner.ru/artifactory/maven")
    }
}
```

Подключение зависимости. Добавьте следующий код в свой конфигурационный файл для подключения зависимости `build.gradle`:

```
dependencies {
    implementation("ru.rustore.sdk:remoteconfig:1.0.0")
}
```

