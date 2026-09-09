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

## История изменений

### Release 10.5.1
- Версия SDK remoteconfig 10.5.1 (BOM `ru.rustore.sdk:bom:2026.08.01`).
- Обновлён Maven-репозиторий на `https://nexus-external.rustore.ru/repository/maven-rustore-exposed`.
- Добавлен метод `getShortSegments()` для получения коротких сегментов конфигурации.
- Переработан UI примера:
  - Добавлен экран выбора поведения обновления `Actual`, `Default`, `Snapshot`;
  - Добавлен экран работы с конфигурацией: поля account/language, кнопки `getRemoteConfig()` и `getShortSegments()`.
- Параметры примера (appId, deviceId, account, language, интервал обновления) вынесены в ресурсы приложения.

### Release 10.1.0
- Версия SDK remoteconfig 10.1.0.

### Release 8.0.0
- Версия SDK remoteconfig 8.0.0.
- Переход на подключение через BOM `ru.rustore.sdk:bom:2025.02.01`.

### Release 6.0.0
- Версия SDK remoteconfig 6.0.0.
- Обновлён способ получения значения конфигурации в примере.

### Release 1.0.0
- Версия SDK remoteconfig 1.0.0.
- Добавлена реализация `RemoteConfigListenerImpl` с колбэками работы SDK.

### Release 0.0.1
- Первая версия примера, SDK remoteconfig 0.0.1.

[en]: CHANGELOG.en.md
