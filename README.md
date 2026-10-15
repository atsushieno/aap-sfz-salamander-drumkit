# Salamander Drumkit SFZ provider

aap-sfz-salamander-drumkit provides [Salamander Drumkit](https://archive.org/details/SalamanderDrumkit) as an SFZ soundpack for Android apps, through the soundpack contract of soundpack-provider-for-android. Apps that use the soundpack client library, such as aap-lv2-sfizz, find it once it is installed. It serves nine SFZ entries from one shared sample tree as read-only descriptor ranges of the APK, without extracting samples.

Version 2.0 replaces the provisional `org.androidaudioplugin.SfzResourceService.V1` contract of 1.x. This is a clean break: install it together with an aap-lv2-sfizz that uses the soundpack libraries. Instruments saved with 1.x restore as unavailable.

## Build

It is an ordinary Android Gradle project that can be opened in Android Studio. Until the soundpack libraries are released, publish them to Maven Local first:

```sh
(cd soundpack-provider-for-android && ./gradlew publishToMavenLocal)
./gradlew assembleDebug
```

All assets are stored uncompressed, because they are served with `AssetManager.openFd()`. The pack is declared in `app/src/main/res/xml/soundpack_sfz.xml`; bump its revision whenever any file under `assets/pack` changes, because saved projects pin it.

The device test runs the contract's conformance checks from a separate test APK:

```sh
./gradlew connectedDebugAndroidTest
```

## Instrument identities

Apps save `commons-soundpack://sfz/org.androidaudioplugin.sfz.salamander-drumkit/<entry>?rev=1.1.0`, where `<entry>` is one of the files below (percent-encoded). These are saved selections, not browser links.

| Entry | Label |
| --- | --- |
| `Salamander Drumkit.sfz` | Full kit |
| `ALL.sfz` | All |
| `crashesFX.sfz` | Crashes / FX |
| `hihat.sfz` | Hi-hat |
| `hitom.sfz` | High tom |
| `kick.sfz` | Kick |
| `lotom.sfz` | Low tom |
| `ride.sfz` | Ride |
| `snare.sfz` | Snare |

## Licenses

Code files in aap-sfz-salamander-drumkit is released under the MIT license.

Salamander Drumkit (included in this repository) is released under Creative Commons Attribution-Share Alike 3.0 license.
