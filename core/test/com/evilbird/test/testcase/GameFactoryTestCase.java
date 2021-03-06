/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.test.testcase;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.engine.game.GameContext;
import com.evilbird.engine.game.GameFactory;
import com.evilbird.test.utils.AssetFileHandleResolver;
import com.evilbird.test.utils.TestAssetManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collection;
import java.util.Map;

/**
 * Instances of this test case provide validation for {@link GameFactory}
 * implementations.
 *
 * @author Blair Butterworth
 */
public abstract class GameFactoryTestCase<T extends GameFactory> extends GameTestCase
{
    protected GameFactory factory;
    protected AssetManager assets;
    protected FileHandleResolver resolver;
    protected DeviceDisplay display;

    @Before
    public void setup() {
        super.setup();
        resolver = new AssetFileHandleResolver();
        assets = TestAssetManager.getTestAssetManager(resolver);
        display = Mockito.mock(DeviceDisplay.class);
        factory = newFactory(display, assets);
    }

    protected abstract T newFactory(DeviceDisplay display, AssetManager assets);

    protected boolean isAssetLoader() {
        return true;
    }

    @Test
    public void loadTest() {
        if (isAssetLoader()) {
            Assert.assertEquals(0, assets.getAssetNames().size);
            for (GameContext context : getLoadContexts()) {
                factory.load(context);
                assets.finishLoading();
                Assert.assertTrue(assets.getAssetNames().size > 0);
            }
        }
    }

    @Test
    public void unloadTest() {
        if (isAssetLoader()) {
            Assert.assertEquals(0, assets.getAssetNames().size);
            for (GameContext context : getLoadContexts()) {
                factory.load(context);
                assets.finishLoading();
                Assert.assertTrue(assets.getAssetNames().size > 0);

                factory.unload(context);
                assets.finishLoading();
                Assert.assertEquals(0, assets.getAssetNames().size);
            }
        }
    }

    @Test
    public void unloadBeforeLoadTest() {
        if (isAssetLoader()) {
            Collection<GameContext> contexts = getLoadContexts();
            GameContext context = contexts.iterator().next();

            factory.unload(context);
            factory.load(context);
        }
    }

    @Test
    public void unloadAfterUnloadTest() {
        if (isAssetLoader()) {
            Collection<GameContext> contexts = getLoadContexts();
            GameContext context = contexts.iterator().next();

            factory.load(context);
            factory.unload(context);
            factory.unload(context);
        }
    }

    protected abstract Collection<GameContext> getLoadContexts();

    @Test
    public void getTest() throws Exception {
        Collection<GameContext> contexts = getLoadContexts();
        GameContext context = contexts.iterator().next();

        factory.load(context);
        assets.finishLoading();

        for (Identifier identifier: getProductIdentifiers()) {
            Object product = factory.get(identifier);
            Assert.assertNotNull(product);
            validateProduct(identifier, product);
        }
    }

    protected abstract Collection<Identifier> getProductIdentifiers();

    protected void validateProduct(Identifier type, Object newObject) throws Exception {
        for (Map.Entry<String, Object> property : getProductProperties().entrySet()) {
            String name = property.getKey();
            Object expected = property.getValue();
            Object actual = MethodUtils.invokeMethod(newObject, "get" + StringUtils.capitalize(name));
            Assert.assertEquals(expected, actual);
        }
    }

    protected abstract Map<String, Object> getProductProperties();
}
