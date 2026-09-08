package org.androidaudioplugin.sfz.salamanderdrumkit;

import android.content.*;
import android.os.*;
import androidx.test.platform.app.InstrumentationRegistry;
import org.junit.Test;
import static org.junit.Assert.*;
import org.androidaudioplugin.sfz.*;
import java.util.*;
import java.util.concurrent.*;

/** Binds from the separate test APK and reads the actual APK descriptor ranges. */
public class ProviderTest {
    @Test
    public void testCatalogAndDescriptorRanges() throws Exception {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        CountDownLatch ready = new CountDownLatch(1);
        ISfzResourceService[] remote = new ISfzResourceService[1];
        ServiceConnection connection = new ServiceConnection() {
            public void onServiceConnected(ComponentName name, IBinder binder) {
                remote[0] = ISfzResourceService.Stub.asInterface(binder); ready.countDown();
            }
            public void onServiceDisconnected(ComponentName name) { }
        };
        Intent intent = new Intent("org.androidaudioplugin.SfzResourceService.V1")
            .setComponent(new ComponentName("org.androidaudioplugin.sfz.salamanderdrumkit",
                "org.androidaudioplugin.sfz.salamanderdrumkit.SalamanderDrumkitService"));
        assertTrue(context.bindService(intent, connection, Context.BIND_AUTO_CREATE));
        try {
            assertTrue(ready.await(10, TimeUnit.SECONDS));
            assertEquals(1, remote[0].getProtocolVersion());
            Bundle[] catalog = remote[0].listInstruments(0, 128);
            assertEquals(9, catalog.length);
            boolean checkedResources = false;
            for (Bundle item : catalog) {
                ISfzResourceSession session = remote[0].openInstrument(item.getString("id"), "1.1.0");
                try {
                    List<String> names = new ArrayList<>();
                    while (true) {
                        String[] page = session.listResources(names.size(), 128);
                        names.addAll(Arrays.asList(page));
                        if (page.length < 128) break;
                    }
                    assertTrue(names.contains(session.getEntryPath()));
                    assertTrue(names.size() <= 2048);
                    try (android.content.res.AssetFileDescriptor afd = session.openResource(session.getEntryPath())) {
                        assertTrue(afd.getLength() > 0);
                        assertTrue(afd.getStartOffset() > 0);
                    }
                    if (!checkedResources) {
                        for (String name : names) {
                            assertFalse(name.startsWith("._"));
                            try (android.content.res.AssetFileDescriptor afd = session.openResource(name);
                                 java.io.InputStream input = afd.createInputStream()) {
                                assertTrue(afd.getDeclaredLength() > 0);
                                assertTrue(input.read() >= 0);
                            }
                        }
                        checkedResources = true;
                    }
                } finally { session.close(); }
            }
        } finally { context.unbindService(connection); }
    }
}
