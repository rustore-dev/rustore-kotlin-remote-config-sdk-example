<!-- ── Language switch (RU active) ──────────────────────────────────── -->
<div align="left" style="margin:0 0 14px 0;">

  <span style="display:inline-block;
               padding:.28rem .6rem;
               border:1px solid rgba(0,0,0,.18);
               border-radius:10px 0 0 10px;
               font-weight:400;
               font-size:12px;
               letter-spacing:.06em;
               color:#111827;
               background:linear-gradient(180deg,#e9edf2,#ffffff);
               box-shadow:inset 0 2px 6px rgba(0,0,0,.10);">
    RU
  </span><span style="display:inline-block;
               margin-left:-1px;
               padding:.28rem .6rem;
               border:1px solid rgba(0,0,0,.14);
               border-radius:0 10px 10px 0;
               font-weight:400;
               font-size:12px;
               letter-spacing:.06em;
               background:linear-gradient(180deg,#ffffff,#f3f4f6);
               box-shadow:0 1px 0 rgba(0,0,0,.06);">
    [EN][en]
  </span>

</div>
<!-- ────────────────────────────────────────────────────────────────── -->

# Пример использования Remote Config SDK от RuStore на kotlin

## [Документация по Remote Config SDK](https://help.rustore.ru/rustore/for_developers/tools/remote_config/sdk)

SDK Remote Config — это облачный сервис, который позволяет изменять поведение и внешний вид вашего приложения, не требуя от пользователей загрузки обновления приложения. SDK инкапсулирует в себе запрос конфигурации с сервера, кэширование, фоновое обновление. Имеет удобный api для получения данных.

Ключевые особенности:

- SDK позволяет выбрать наиболее удобный механизм обновления конфигурации.
- Имеет возможность указывать процент распространения конфигурации на аудиторию.
- Для построения воронки конкретной конфигурации можно передавать дополнительную информацию. Возможно формировать конфигурации даже для конкретных пользователей.
- Имеет набор колбэков о работе SDK, которые можно использовать для аналитики.
- Имеет минимальное количество внешних зависимостей.

## О примере

Приложение состоит из двух экранов:

1. **Выбор поведения обновления** — стартовый экран при каждом запуске: `Actual`, `Default` или `Snapshot` (текущее помечено `(selected)`). Выбор сохраняется, приложение перезапускается, и `RemoteConfigClient` создаётся и инициализируется в `Application.onCreate()` с выбранным поведением (интервал задаётся ресурсом `rustore_update_time`). Кнопка `Continue` открывает экран конфигурации.
2. **Работа с конфигурацией**:
   - стрелка назад — возврат к выбору поведения;
   - поля `account` и `language` — параметры запроса конфигурации;
   - `getRemoteConfig()` — получение конфигурации (содержимое выводится в лог в виде JSON);
   - `getShortSegments()` — получение коротких сегментов A/B-тестов;
   - события SDK (включая инициализацию из `Application.onCreate`) и результаты операций отображаются в логе на весь оставшийся экран; ошибки выделяются розовым фоном.

Параметры примера (`rustore_app_id`, `rustore_device_id`, `rustore_account`, `rustore_language`, `rustore_update_behaviour`) преднастроены в `app/src/main/res/values/strings.xml`. Значения, изменённые в UI (account, language, поведение), сохраняются в SharedPreferences и имеют приоритет над значениями по умолчанию из ресурсов; смена поведения применяется после перезапуска приложения.

## История изменений

См. [CHANGELOG.md](CHANGELOG.md).

## Подключение

Подключите репозиторий `build.gradle`:

```
repositories {
    maven {
        url = uri("https://nexus-external.rustore.ru/repository/maven-rustore-exposed")
    }
}
```

Подключение зависимости через BOM. Добавьте следующий код в свой `build.gradle`:

```
dependencies {
    implementation(platform("ru.rustore.sdk:bom:2026.08.01"))
    implementation("ru.rustore.sdk:remoteconfig")
}
```

Подключение зависимости напрямую. Добавьте следующий код в свой `build.gradle`:

```
dependencies {
    implementation("ru.rustore.sdk:remoteconfig:10.5.1")
}
```

[en]: README.en.md
