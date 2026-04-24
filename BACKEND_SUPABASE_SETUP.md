# Blue Peach Mobile Backend/Supabase Setup

This Android app is prepared to share the same backend and Supabase project as the web app.

## 1) Environment mapping (web -> Android)

- Web frontend:
  - `NEXT_PUBLIC_API_URL` (default `http://localhost:4000/api`)
  - `NEXT_PUBLIC_SUPABASE_URL`
  - `NEXT_PUBLIC_SUPABASE_ANON_KEY`
- Web backend:
  - `SUPABASE_URL`
  - `SUPABASE_SERVICE_ROLE_KEY` (server-only)
- Android (this repo):
  - `BLUE_PEACH_API_BASE_URL`
  - `BLUE_PEACH_SUPABASE_URL`
  - `BLUE_PEACH_SUPABASE_ANON_KEY`

Important: `SUPABASE_SERVICE_ROLE_KEY` must never be shipped in mobile app.

## 2) Configure Android local properties

1. Copy `local.properties.example` -> `local.properties`.
2. Keep your own `sdk.dir` line.
3. Fill values:

```properties
BLUE_PEACH_API_BASE_URL=http://10.0.2.2:4000/api
BLUE_PEACH_SUPABASE_URL=https://<your-project>.supabase.co
BLUE_PEACH_SUPABASE_ANON_KEY=<your-anon-key>
```

Notes:
- Android emulator must use `10.0.2.2` for host machine localhost.
- Physical device should use LAN IP, for example `http://192.168.1.10:4000/api`.

## 3) Runtime behavior in app

- Values are injected from Gradle into `BuildConfig`.
- App config holder: `com.bluepeach.app.core.network.NetworkConfig`.
- Startup validator logs warnings if config is missing:
  - `com.bluepeach.app.core.network.RuntimeConfigValidator`.
- Shared backend endpoint contract constants:
  - `com.bluepeach.app.core.network.BackendApiContract`.

## 4) Auth compatibility with web flow

Web frontend attaches Supabase access token as:
- `Authorization: Bearer <access_token>`

Android prepared equivalent utility:
- `com.bluepeach.app.core.network.NetworkAuthKt` (`buildJsonHeaders`, `AccessTokenProvider`).

When Supabase auth is added in a later step, provide token through `AccessTokenProvider`.
