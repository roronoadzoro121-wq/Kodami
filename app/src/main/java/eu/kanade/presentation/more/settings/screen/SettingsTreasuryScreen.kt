package eu.kanade.presentation.more.settings.screen

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import cafe.adriel.voyager.navigator.LocalNavigator
import eu.kanade.domain.ui.KodamiTreasuryPreferences
import eu.kanade.domain.ui.KomikkuCustomisationPreferences
import eu.kanade.domain.ui.UiPreferences
import eu.kanade.domain.ui.model.AppTheme
import eu.kanade.presentation.more.settings.Preference
import eu.kanade.presentation.more.settings.PreferenceScaffold
import eu.kanade.presentation.util.Screen
import eu.kanade.presentation.more.settings.screen.SettingsKomikkuCustomisationScreen
import eu.kanade.presentation.more.settings.screen.SettingsDoujinCustomisationsScreen
import eu.kanade.presentation.more.settings.screen.SettingsReaderScreen
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.persistentMapOf
import tachiyomi.core.common.preference.Preference as PreferenceData
import tachiyomi.core.common.preference.PreferenceStore
import tachiyomi.i18n.kmk.KMR
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

object SettingsTreasuryScreen : Screen() {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        val context = LocalContext.current
        val uiPreferences = androidx.compose.runtime.remember { Injekt.get<UiPreferences>() }
        val treasury = androidx.compose.runtime.remember {
            KodamiTreasuryPreferences(Injekt.get<PreferenceStore>())
        }
        val customisation = androidx.compose.runtime.remember {
            KomikkuCustomisationPreferences(Injekt.get<PreferenceStore>())
        }
        val themeOptions = androidx.compose.runtime.remember {
            AppTheme.entries.filter { it.titleRes != null }.associateWith { theme ->
                when (theme) {
                    AppTheme.ONYX_GOLD -> "Onyx Gold"
                    AppTheme.SAKURA_NOIR -> "Sakura Noir"
                    AppTheme.NEBULA_TIDE -> "Nebula Tide"
                    AppTheme.EVENT_HORIZON -> "Event Horizon"
                    AppTheme.VOID_RED -> "Blood of Lilith"
                    AppTheme.AURORA_PRIME -> "Aurora Prime"
                    AppTheme.LATTICE_PROTOCOL -> "Lattice Protocol"
                    else -> theme.name.lowercase().split('_').joinToString(" ") { part ->
                        part.replaceFirstChar { it.uppercase() }
                    }
                }
            }
        }

        PreferenceScaffold(
            titleRes = KMR.strings.pref_category_treasury,
            onBackPressed = { navigator?.pop() },
            itemsProvider = {
                listOf(
                    Preference.PreferenceGroup(
                        title = "Treasury themes",
                        preferenceItems = persistentListOf(
                            Preference.PreferenceItem.InfoPreference(
                                "Every Treasury item is available immediately in Kodami—no achievements, riddles, or unlocks are required.",
                            ),
                            Preference.PreferenceItem.ListPreference(
                                preference = uiPreferences.appTheme(),
                                entries = persistentMapOf(*themeOptions.toList().toTypedArray()),
                                title = "App color theme",
                                subtitle = "%s",
                                onValueChanged = {
                                    (context as? Activity)?.let { ActivityCompat.recreate(it) }
                                    true
                                },
                            ),
                        ),
                    ),
                    Preference.PreferenceGroup(
                        title = "Background effects",
                        preferenceItems = persistentListOf(
                            listPreference(
                                preference = treasury.backgroundEffect(),
                                title = "Background effect",
                                values = linkedMapOf(
                                    "none" to "Off",
                                    "petal_storm" to "Petal Storm",
                                    "neon_orbit" to "Neon Orbit",
                                    "trinity_constellation" to "Trinity Constellation",
                                    "deep_space_archive" to "Deep Space Archive",
                                    "shadow_realm" to "Shadow Realm",
                                    "event_horizon_library" to "Event Horizon Library",
                                    "void_weeping_red" to "Weeping Void",
                                    "ink_water" to "Ink in Water",
                                ),
                            ),
                            Preference.PreferenceItem.InfoPreference(
                                "Background effects appear behind the More and Library screens. They stay static to keep scrolling smooth.",
                            ),
                        ),
                    ),
                    Preference.PreferenceGroup(
                        title = "Profile options",
                        preferenceItems = persistentListOf(
                            Preference.PreferenceItem.EditTextPreference(
                                preference = treasury.profileName(),
                                title = "Profile name",
                                subtitle = "%s",
                            ),
                            Preference.PreferenceItem.EditTextPreference(
                                preference = treasury.profileTagline(),
                                title = "Profile status line",
                                subtitle = "%s",
                            ),
                            listPreference(
                                preference = treasury.profileTitle(),
                                title = "Profile title",
                                values = linkedMapOf(
                                    "none" to "No title",
                                    "title_trinity_initiate" to "Trinity Initiate",
                                    "title_finisher" to "Finisher",
                                    "title_closer" to "Closer",
                                    "title_deep_reader" to "Deep Reader",
                                    "title_rank_4" to "Rank 4",
                                ),
                            ),
                            listPreference(
                                preference = treasury.nicknameEffect(),
                                title = "Name effect",
                                values = linkedMapOf(
                                    "none" to "Off",
                                    "aurora_crown" to "Aurora Crown",
                                    "glitch_rune" to "Glitch Rune",
                                    "glitch_rune_red" to "Crimson Glitch",
                                    "cipher" to "Cipher",
                                    "trinity_prism" to "Trinity Prism",
                                    "shadow_crown" to "Shadow Crown",
                                    "rank_sigils" to "Rank Sigils",
                                ),
                            ),
                            listPreference(
                                preference = treasury.avatarFrame(),
                                title = "Avatar frame",
                                values = linkedMapOf(
                                    "none" to "Off",
                                    "glitch_red" to "Glitch Red",
                                    "neon" to "Neon",
                                    "hologram" to "Hologram",
                                    "prismatic" to "Prismatic",
                                    "trinity_orbit" to "Trinity Orbit",
                                    "deep_archive" to "Deep Archive",
                                    "hybrid_scroll" to "Hybrid Scroll",
                                    "ascendant" to "Ascendant",
                                ),
                            ),
                            listPreference(
                                preference = treasury.homeBadge(),
                                title = "Profile badge",
                                values = linkedMapOf(
                                    "none" to "Off",
                                    "orbit" to "Orbit",
                                    "crown" to "Crown",
                                    "shuriken" to "Shuriken",
                                    "trinity" to "Trinity",
                                    "finisher" to "Finisher",
                                    "immersion" to "Immersion",
                                    "ascendant" to "Ascendant",
                                ),
                            ),
                        ),
                    ),
                    Preference.PreferenceGroup(
                        title = "Manga and reading options",
                        preferenceItems = persistentListOf(
                            Preference.PreferenceItem.TextPreference(
                                title = "Manga customization",
                                subtitle = "Library cards, manga details, discovery, filters, and manga-specific tools",
                                onClick = { navigator?.push(SettingsDoujinCustomisationsScreen) },
                            ),
                            Preference.PreferenceItem.TextPreference(
                                title = "Kodami customization",
                                subtitle = "Library layout, manga cards, reader profiles, and app-wide options",
                                onClick = { navigator?.push(SettingsKomikkuCustomisationScreen) },
                            ),
                            Preference.PreferenceItem.TextPreference(
                                title = "Reader settings",
                                subtitle = "Page layout, reading direction, scaling, gestures, and other reader options",
                                onClick = { navigator?.push(SettingsReaderScreen) },
                            ),
                        ),
                    ),
                    Preference.PreferenceGroup(
                        title = "Performance",
                        preferenceItems = persistentListOf(
                            listPreference(
                                preference = customisation.performanceMode(),
                                title = "Performance mode",
                                values = linkedMapOf(
                                    "performance" to "Fast and smooth",
                                    "balanced" to "Balanced",
                                    "battery" to "Battery saver",
                                    "custom" to "Custom",
                                ),
                            ),
                            Preference.PreferenceItem.InfoPreference(
                                "Fast and smooth reduces costly cover color sampling, shadows, gradients, and cover-based theme animation without deleting your saved appearance choices.",
                            ),
                        ),
                    ),
                )
            },
        )
    }

    @Composable
    private fun <T> listPreference(
        preference: PreferenceData<T>,
        title: String,
        values: Map<T, String>,
    ) = Preference.PreferenceItem.ListPreference(
        preference = preference,
        entries = persistentMapOf(*values.toList().toTypedArray()),
        title = title,
        subtitle = "%s",
    )
}
