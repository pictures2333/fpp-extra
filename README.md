# FPP Extra

A small Paper plugin for [Fake Player Plugin 2.0 (FPP)](https://modrinth.com/plugin/fake-player-plugin-fpp) bots at AFK farms. Exclude bots from night-skipping requirements and help keep them alive while leaving regular players alone.

## Installation

Use Paper 26.3 with Java 25. Install FPP first, then copy `fpp-extra-1.0.0.jar` into your server's `plugins/` directory and restart the server.

## Commands and permissions

| Command | Description | Permission |
| --- | --- | --- |
| `/fpp-extra listbots` | List detected bots, their UUIDs, and the total count. | `fpp-extra.listbots` |
| `/fpp-extra status` | Show whether each feature is enabled. | `fpp-extra.status` |
| `/fpp-extra ignore-sleep <on/off>` | Toggle whether bots count toward night-skipping requirements. | `fpp-extra.switch` |
| `/fpp-extra invulnerable <on/off>` | Toggle bot protection. | `fpp-extra.switch` |
| `/fpp-extra reload` | Reload the configuration from disk. | `fpp-extra.reload` |

- `fpp-extra.admin` grants access to all commands and is enabled for operators by default.
- `fpp-extra.user` grants access to `listbots` and `status`.

Individual command permissions default to false; grant them directly or through the groups above.

## Configuration

On first startup, the plugin creates `plugins/fpp-extra/config.yml` with both features disabled:

```yaml
ignore-sleep: false
invulnerable: false
```

Toggle commands save changes automatically. After editing the file manually, run `/fpp-extra reload`.

## How it works

Every 20 ticks (about one second), the plugin checks all worlds for bots and applies the current settings. Newly spawned bots are picked up automatically; the task stops when the plugin is disabled.

- **Ignore sleep** excludes bots from the players needed to skip the night.
- **Protection** restores living bots to full health and refreshes strong Resistance and Regeneration effects lasting 40 ticks. After disabling it, existing effects expire naturally. This does not cancel damage events or guarantee complete invulnerability.

Bots are identified by UUID rather than name, using this pattern:

```regex
^fb07[0-9a-f]{4}-[0-9a-f]{4}-0[0-9a-f]{3}-[0-9a-f]{4}-[0-9a-f]{12}$
```

This checks FPP's `fb07` prefix and version nibble `0`, rather than using an official fake-player API. Any matching UUID is treated as a bot; FPP format changes may require an update.

The build targets Paper 26.3; compatibility with other versions is not established. Folia is not supported.

## Building

With JDK 25 installed, run:

```sh
./gradlew build
```

On Windows, use:

```
gradlew.bat build
````

The compiled plugin is saved as `build/libs/fpp-extra-1.0.0.jar`.
