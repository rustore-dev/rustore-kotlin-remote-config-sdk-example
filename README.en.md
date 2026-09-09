<!-- ── Language switch (EN active) ──────────────────────────────────── -->
<div align="left" style="margin:0 0 14px 0;">

  <span style="display:inline-block;
               padding:.28rem .6rem;
               border:1px solid rgba(0,0,0,.18);
               border-radius:10px 0 0 10px;
               font-weight:400;
               font-size:12px;
               letter-spacing:.06em;
               color:#111827;
               background:linear-gradient(180deg,#ffffff,#e9edf2);
               box-shadow:0 1px 0 rgba(0,0,0,.06);">
    [RU][ru]
  </span><span style="display:inline-block;
               margin-left:-1px;
               padding:.28rem .6rem;
               border:1px solid rgba(0,0,0,.14);
               border-radius:0 10px 10px 0;
               font-weight:400;
               font-size:12px;
               letter-spacing:.06em;
               background:linear-gradient(180deg,#e9edf2,#ffffff);
               box-shadow:inset 0 2px 6px rgba(0,0,0,.10);">
    EN
  </span>

</div>
<!-- ────────────────────────────────────────────────────────────────── -->

# Example of using the RuStore Remote Config SDK in kotlin

## [Remote Config SDK Documentation](https://help.rustore.ru/en/rustore/for_developers/tools/remote_config/sdk)

The Remote Config SDK is a cloud service that allows you to change the behavior and appearance of your application without requiring users to download an app update. The SDK encapsulates requests for configuration from the server, caching, and background updates. It has a convenient API for retrieving data.

Key features:

- The SDK allows you to choose the most convenient mechanism for updating the configuration.
- It allows specifying the percentage of audience distribution for the configuration.
- For building a funnel for a specific configuration, you can pass additional information. You can even create configurations for specific users.
- It has a set of SDK callbacks that can be used for analytics.
- It has a minimal number of external dependencies.

## About the example

The application consists of two screens:

1. **Update behaviour selection** — the start screen on every launch: `Actual`, `Default` or `Snapshot` (the current one is marked `(selected)`). The choice is saved, the app restarts, and `RemoteConfigClient` is created and initialized in `Application.onCreate()` with the selected behaviour (the interval is set by the `rustore_update_time` resource). The `Continue` button opens the config screen.
2. **Config screen**:
   - back arrow — returns to the behaviour selection;
   - `account` and `language` fields — config request parameters;
   - `getRemoteConfig()` — retrieves the config (its contents are shown in the log as JSON);
   - `getShortSegments()` — retrieves short A/B-test segments;
   - SDK events (including initialization from `Application.onCreate`) and operation results are shown in a log that fills the remaining screen; error lines are highlighted with a pink background.

Example parameters (`rustore_app_id`, `rustore_device_id`, `rustore_account`, `rustore_language`, `rustore_update_behaviour`) are preconfigured in `app/src/main/res/values/strings.xml`. Values changed in the UI (account, language, behaviour) are stored in SharedPreferences and take precedence over the resource defaults; a behaviour change is applied after the app restarts.

## Changelog

See [CHANGELOG.en.md](CHANGELOG.en.md).

## Integration

Add the repository to `build.gradle`:

```
repositories {
    maven {
        url = uri("https://nexus-external.rustore.ru/repository/maven-rustore-exposed")
    }
}
```

Include dependency via BOM. Add the following code to your `build.gradle`:

```
dependencies {
    implementation(platform("ru.rustore.sdk:bom:2026.08.01"))
    implementation("ru.rustore.sdk:remoteconfig")
}
```

Direct dependency inclusion. Add the following code to your `build.gradle`:

```
dependencies {
    implementation("ru.rustore.sdk:remoteconfig:10.5.1")
}
```

[ru]: README.md
