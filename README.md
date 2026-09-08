# Salamander Drumkit SFZ provider

aap-sfz-salamander-drumkit provides [Salamander Drumkit](https://archive.org/details/SalamanderDrumkit) as a standalone Android asset provider for the provisional
`org.androidaudioplugin.SfzResourceService.V1` contract. Install alongside the
resource-enabled aap-lv2-sfizz app, open its SFZ picker, and select Find SFZ packs.
This APK has no launcher activity. It serves nine SFZ entries from one shared
sample namespace through read-only APK descriptor ranges, without extracting samples.

## Build

It is an ordinary Android Gradle project that can be opened in Android Studio.

The Gradle wrapper and provider contract sources are vendored so the project does
not depend on a sibling checkout or the original external volume. Provider sources
were copied from `../aap-lv2-sfizz/sfz-provider` on 2026-09-07; keep both AIDL files
wire-compatible when updating them. All assets are stored uncompressed for openFd.

## Instrument URIs

Each URI is `aap-sfz://org.androidaudioplugin.sfz.salamanderdrumkit/org.androidaudioplugin.sfz.salamanderdrumkit.SalamanderDrumkitService/ID?revision=1.1.0`.
These are sfizz selection identities, not browser links or ContentProvider URLs.

| ID | Entry SFZ |
| --- | --- |
| salamander-drumkit | Salamander Drumkit.sfz |
| all | ALL.sfz |
| crashes-fx | crashesFX.sfz |
| hihat | hihat.sfz |
| hitom | hitom.sfz |
| kick | kick.sfz |
| lotom | lotom.sfz |
| ride | ride.sfz |
| snare | snare.sfz |

Increment the immutable revision when changing resource bytes or names.

## Licenses

Code files in aap-sfz-salamander-drumkit is released under the MIT license.

Salamander Drumkit (included in this repository) is released under Creative Commons Attribution-Share Alike 3.0 license.
