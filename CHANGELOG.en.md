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

## Changelog

### Release 10.5.1
- remoteconfig SDK version 10.5.1 (BOM `ru.rustore.sdk:bom:2026.08.01`).
- Updated Maven repository to `https://nexus-external.rustore.ru/repository/maven-rustore-exposed`.
- Added `getShortSegments()` method for retrieving short config segments.
- The example UI was reworked:
  - Added an update behaviour selection screen (`Actual`, `Default`, `Snapshot`);
  - Added a config screen with account/language fields and `getRemoteConfig()` / `getShortSegments()` buttons.
- Example parameters (appId, deviceId, account, language, update interval) are moved into app resources.

### Release 10.1.0
- remoteconfig SDK version 10.1.0.

### Release 8.0.0
- remoteconfig SDK version 8.0.0.
- Switched to the `ru.rustore.sdk:bom:2025.02.01` BOM dependency.

### Release 6.0.0
- remoteconfig SDK version 6.0.0.
- Updated the config value retrieval in the example.

### Release 1.0.0
- remoteconfig SDK version 1.0.0.
- Added `RemoteConfigListenerImpl` with SDK event callbacks.

### Release 0.0.1
- First example release, remoteconfig SDK 0.0.1.

[ru]: CHANGELOG.md
