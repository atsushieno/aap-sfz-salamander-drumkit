package org.androidaudioplugin.sfz.salamanderdrumkit

import android.net.Uri
import androidx.test.platform.app.InstrumentationRegistry
import org.androidaudioplugin.soundpack.SoundpackContract
import org.androidaudioplugin.soundpack.testkit.ProviderConformance
import org.junit.Assert.assertEquals
import org.junit.Test

/** Reads the pack from the separate test APK, so every call crosses processes. */
class ProviderTest {
    private val context = InstrumentationRegistry.getInstrumentation().context
    private val authority = "org.androidaudioplugin.sfz.salamanderdrumkit.soundpack.sfz"

    @Test
    fun conformsToTheSoundpackContract() {
        ProviderConformance(context, authority).assertConforms()
    }

    @Test
    fun servesTheNineInstruments() {
        val uri = Uri.parse("content://$authority/packs/org.androidaudioplugin.sfz.salamander-drumkit/entries")
        val entries = context.contentResolver.query(uri, null, null, null, null)!!.use { cursor ->
            buildList { while (cursor.moveToNext()) add(cursor.getString(cursor.getColumnIndexOrThrow(SoundpackContract.Entries.ENTRY_PATH))) }
        }
        assertEquals(setOf("Salamander Drumkit.sfz", "ALL.sfz", "crashesFX.sfz", "hihat.sfz", "hitom.sfz",
            "kick.sfz", "lotom.sfz", "ride.sfz", "snare.sfz"), entries.toSet())
    }
}
