# Kodami manga-only Tadami port inventory

Baseline comparison: Tadami-Aniyomi-fork `6382175be690261a9cbeeae862bbbee33a04b44e` versus Kodami `b302c70b6fd744be07297300fb325055ce01c0ba`.

## Scope

Port only manga-facing UI/design/customisation into Kodami. Preserve Kodami/Komikku Home and the existing Komikku reader. Anime, light-novel, player, novel-reader, and Home-only implementations are excluded.

## Component mapping

| Tadami component family | Kodami/Komikku equivalent | Port decision |
|---|---|---|
| `presentation/entries/manga/MangaScreenAurora.kt` | `presentation/manga/MangaScreen.kt` and `MangaInfoHeader.kt` | Adapt manga presentation only; keep Komikku state, actions, chapter handling, tracking, notes, migration, downloads, and source behavior. |
| `presentation/entries/manga/components/aurora/*` | `presentation/manga/components/*` | Adapt hero, metadata, chapter rows, progress, tags, actions, and responsive surfaces without importing AniYomi entry models. |
| `presentation/components/AuroraCard.kt`, `AuroraComponents.kt`, `GlassmorphismCard.kt` | Existing Compose Material3 card/surface helpers | Recreate the reusable manga-safe visual primitives; do not replace Home surfaces. |
| `presentation/library/manga/MangaLibraryAuroraContent.kt` | `presentation/library/components/*`, `LibraryPager.kt`, `LibraryContent.kt` | Adapt card surfaces, spacing, progress/unread badges, selection, categories, sorting, filtering, grouping, and large-library lazy rendering. |
| `presentation/browse/manga/*` | `presentation/browse/*` and source/search screens | Adapt source cards, search results, filters, tabs, empty/loading/error states while preserving extension/source behavior. |
| `presentation/more/settings/screen/SettingsTreasuryScreen.kt` | Kodami visual-customisation settings | Recreate manga-facing controls; all selected Treasury visual effects are available immediately with no achievement gate. |
| `presentation/theme/AuroraTheme.kt`, `AuroraSurfaceTokens.kt` | Kodami `TachiyomiTheme.kt` and shared Compose components | Integrate reusable Aurora/Glass tokens through existing Material3 theme architecture. |
| Tadami reader additions | Komikku reader | Compare functionally; retain Komikku implementation unless a genuinely missing, low-risk feature is independently added. No reader visual transplant. |

## Treasury coverage

The manga-facing customisation selector includes every named/general visual effect found in Tadami's Treasury implementation, including Blood of Lilith, Weeping Void, Core Melt, Crimson Glitch, Void Red, Aurora Prime, Onyx Gold, Sakura Noir, Nebula Tide, Event Horizon, Lattice Protocol, Ink Water, Petal Storm, Neon Orbit, Trinity Constellation, Deep Space Archive, Shadow Realm, and all frame variants. Effects are exposed as presentation choices for manga cards/title surfaces; they are not tied to achievements, unlock progress, or profile state.

Profile-only nickname/home-badge behavior is not transplanted into Komikku Home. Where a frame effect is selected, it is rendered as a manga-card/title treatment instead of changing unrelated profile or Home UI.

## Explicit exclusions

- Tadami Home hero, banner, cards, navigation, animations, and Home-only effects.
- Anime screens, anime-player behavior, novel screens, novel-reader behavior, and their data models.
- Replacing Komikku's reader engine or reader visual design.
- Rewriting the database, download engine, source/extension architecture, or manga functionality.
- Achievement-gated access: disabled for this port; all manga Treasury effects are available immediately.

## Verification checklist

- [ ] Home remains on Komikku implementation.
- [ ] Existing manga actions and chapter state remain intact.
- [ ] Library remains lazy and responsive for large collections.
- [ ] Manga title/details screen retains Komikku callbacks and data.
- [ ] Browse/search/source functionality remains intact.
- [ ] Treasury options are user-selectable and not achievement-locked.
- [ ] Phone/tablet and portrait/landscape layouts compile and are reviewed.
- [ ] Light/dark themes compile and are reviewed.
- [ ] APK ZIP integrity, manifest, package/version, and signing are checked.
