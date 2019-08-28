/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.test.data.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.device.DeviceDisplay;
import org.mockito.Mockito;

import static com.evilbird.test.data.assets.TestAssets.newAssetManagerMock;

public class TestDevices
{
    private TestDevices() {
    }

    public static Device newTestDevice() {
        AssetManager assets = newAssetManagerMock();
        DeviceDisplay display = Mockito.mock(DeviceDisplay.class);
        Mockito.when(display.getScaleFactor()).thenReturn(1f);

        Device device = Mockito.mock(Device.class);
        Mockito.when(device.getAssetStorage()).thenReturn(assets);
        Mockito.when(device.getDeviceDisplay()).thenReturn(display);

        return device;
    }
}
