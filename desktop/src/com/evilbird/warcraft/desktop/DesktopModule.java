/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.desktop;

import com.evilbird.engine.device.Device;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Defines the bindings between the device services and their desktop
 * implementations.
 *
 * @author Blair Butterworth
 */
@Module
public class DesktopModule
{
    private DesktopModule() {
    }

    @Provides
    @Singleton
    public static Device provideDevice() {
        return new DesktopDevice();
    }
}
