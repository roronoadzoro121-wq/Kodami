package eu.kanade.presentation.more

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.dp
import eu.kanade.domain.ui.KodamiTreasuryPreferences
import tachiyomi.core.common.preference.PreferenceStore
import tachiyomi.presentation.core.util.collectAsState
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

/** Static, low-cost versions of Tadami's Treasury background motifs for the More screen. */
@Composable
internal fun KodamiTreasuryBackdrop(modifier: Modifier = Modifier) {
    val preferences = remember { KodamiTreasuryPreferences(Injekt.get<PreferenceStore>()) }
    val effect by preferences.backgroundEffect().collectAsState()
    if (effect == "none") return

    Canvas(modifier = modifier.fillMaxSize()) {
        val w = size.width
        val h = size.height
        val violet = Color(0xFF9C7CFF)
        val cyan = Color(0xFF5DE7D8)
        val gold = Color(0xFFFFD36E)
        when (effect) {
            "petal_storm" -> {
                drawRect(Brush.verticalGradient(listOf(Color(0xFFFF8FB1).copy(alpha = 0.07f), Color.Transparent)))
                repeat(30) { i ->
                    val x = ((i * 83 + 19) % 997) / 997f * w
                    val y = ((i * 137 + 51) % 991) / 991f * h
                    rotate((i * 29 % 80 - 40).toFloat(), Offset(x, y)) {
                        drawOval(
                            color = Color(0xFFFF8FB1).copy(alpha = 0.08f + (i % 3) * 0.025f),
                            topLeft = Offset(x, y),
                            size = Size(15.dp.toPx(), 7.dp.toPx()),
                        )
                    }
                }
            }
            "neon_orbit" -> {
                drawRect(Brush.radialGradient(listOf(cyan.copy(alpha = 0.12f), Color.Transparent), center = Offset(w * 0.78f, h * 0.3f), radius = w * 0.8f))
                repeat(4) { i ->
                    drawOval(
                        color = cyan.copy(alpha = 0.12f - i * 0.018f),
                        topLeft = Offset(w * (0.43f - i * 0.04f), h * (0.17f - i * 0.025f)),
                        size = Size(w * (0.52f + i * 0.08f), h * (0.24f + i * 0.05f)),
                        style = Stroke(width = (1 + i % 2).dp.toPx()),
                    )
                }
            }
            "trinity_constellation" -> {
                val points = listOf(Offset(w * 0.16f, h * 0.24f), Offset(w * 0.82f, h * 0.34f), Offset(w * 0.53f, h * 0.78f))
                drawLine(violet.copy(alpha = 0.16f), points[0], points[1], 1.dp.toPx())
                drawLine(cyan.copy(alpha = 0.14f), points[1], points[2], 1.dp.toPx())
                drawLine(gold.copy(alpha = 0.13f), points[2], points[0], 1.dp.toPx())
                points.forEachIndexed { i, point -> drawCircle(listOf(violet, cyan, gold)[i].copy(alpha = 0.26f), 4.dp.toPx(), point) }
                repeat(28) { i ->
                    val x = ((i * 191 + 37) % 997) / 997f * w
                    val y = ((i * 79 + 11) % 991) / 991f * h
                    drawCircle(Color.White.copy(alpha = 0.20f), (i % 3 + 1).dp.toPx(), Offset(x, y))
                }
            }
            "deep_space_archive" -> {
                drawRect(Brush.verticalGradient(listOf(Color(0xFF071D2D).copy(alpha = 0.16f), Color.Transparent)))
                repeat(8) { row ->
                    val y = h * (0.18f + row * 0.1f)
                    drawLine(cyan.copy(alpha = 0.10f), Offset(w * 0.06f, y), Offset(w * 0.96f, y), 1.dp.toPx())
                    repeat(9) { col ->
                        val x = w * (0.08f + col * 0.105f)
                        val bookH = h * (0.025f + ((row + col) % 4) * 0.008f)
                        drawLine(listOf(cyan, violet, gold)[(row + col) % 3].copy(alpha = 0.12f), Offset(x, y), Offset(x, y - bookH), 2.dp.toPx())
                    }
                }
            }
            "shadow_realm" -> {
                drawRect(Brush.radialGradient(listOf(violet.copy(alpha = 0.12f), Color(0xFF08030D).copy(alpha = 0.06f), Color.Transparent), center = Offset(w * 0.52f, h * 0.52f), radius = w * 0.95f))
                repeat(5) { i ->
                    drawOval(Color(0xFF1B0D2A).copy(alpha = 0.07f), Offset(w * (0.12f + i * 0.04f), h * (0.2f + i * 0.12f)), Size(w * 0.78f, h * 0.13f), style = Stroke(width = 2.dp.toPx()))
                }
            }
            "event_horizon_library" -> {
                drawRect(Brush.radialGradient(listOf(Color(0xFF1A4FE0).copy(alpha = 0.15f), Color(0xFF071127).copy(alpha = 0.06f), Color.Transparent), center = Offset(w * 0.5f, h * 0.4f), radius = w * 0.65f))
                repeat(7) { i ->
                    drawOval(Color(0xFF5DE7D8).copy(alpha = 0.11f - i * 0.01f), Offset(w * (0.25f - i * 0.02f), h * (0.28f - i * 0.025f)), Size(w * (0.5f + i * 0.04f), h * (0.16f + i * 0.025f)), style = Stroke(width = 1.dp.toPx()))
                }
            }
            "void_weeping_red" -> {
                drawRect(Brush.verticalGradient(listOf(Color(0xFF33000A).copy(alpha = 0.16f), Color.Transparent)))
                repeat(24) { i ->
                    val x = ((i * 89 + 31) % 991) / 991f * w
                    val y = ((i * 163 + 17) % 997) / 997f * h
                    val length = h * (0.035f + (i % 5) * 0.016f)
                    drawLine(Color(0xFFFF1E27).copy(alpha = 0.08f + (i % 4) * 0.025f), Offset(x, y), Offset(x, y + length), (1 + i % 2).dp.toPx())
                    drawCircle(Color(0xFFFF1E27).copy(alpha = 0.10f), 2.dp.toPx(), Offset(x, y + length))
                }
            }
            "ink_water" -> {
                drawRect(Brush.radialGradient(listOf(Color(0xFF6C7CE0).copy(alpha = 0.12f), Color.Transparent), center = Offset(w * 0.48f, h * 0.42f), radius = w * 0.72f))
                repeat(7) { i ->
                    val path = Path().apply {
                        moveTo(-w * 0.05f, h * (0.26f + i * 0.08f))
                        cubicTo(w * 0.26f, h * (0.05f + i * 0.1f), w * 0.68f, h * (0.52f - i * 0.04f), w * 1.05f, h * (0.22f + i * 0.09f))
                    }
                    drawPath(path, Color(0xFF8998FF).copy(alpha = 0.08f + (i % 3) * 0.025f), style = Stroke(width = (1 + i % 2).dp.toPx()))
                }
            }
        }
    }
}
