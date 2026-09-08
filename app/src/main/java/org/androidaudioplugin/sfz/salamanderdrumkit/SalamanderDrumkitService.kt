package org.androidaudioplugin.sfz.salamanderdrumkit

import org.androidaudioplugin.sfz.AssetSfzResourceService

/** All entries share one immutable sample namespace. IDs are persistent state. */
class SalamanderDrumkitService : AssetSfzResourceService() {
    override val instruments = listOf(
        instrument("salamander-drumkit", "Salamander Drumkit", "Salamander Drumkit.sfz"),
        instrument("all", "Salamander Drumkit — ALL", "ALL.sfz"),
        instrument("crashes-fx", "Salamander Drumkit — Crashes / FX", "crashesFX.sfz"),
        instrument("hihat", "Salamander Drumkit — Hi-hat", "hihat.sfz"),
        instrument("hitom", "Salamander Drumkit — High tom", "hitom.sfz"),
        instrument("kick", "Salamander Drumkit — Kick", "kick.sfz"),
        instrument("lotom", "Salamander Drumkit — Low tom", "lotom.sfz"),
        instrument("ride", "Salamander Drumkit — Ride", "ride.sfz"),
        instrument("snare", "Salamander Drumkit — Snare", "snare.sfz")
    )

    private fun instrument(id: String, label: String, entry: String) =
        Instrument(id, label, "1.1.0", "pack", entry)
}
