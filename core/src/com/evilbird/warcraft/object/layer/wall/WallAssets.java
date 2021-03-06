/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.layer.wall;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.assets.AssetBundle;
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.warcraft.state.WarcraftContext;

import java.util.Map;

import static com.evilbird.engine.common.text.StringUtils.toSnakeCase;

/**
 * Provides access to the assets that are required to display a {@link Wall}.
 *
 * @author Blair Butterworth
 */
public class WallAssets extends AssetBundle
{
    public WallAssets(AssetManager manager, WarcraftContext context) {
        super(manager, assetPathVariables(context));
        register("terrain", "data/textures/common/terrain/${season}.png");
    }

    private static Map<String, String> assetPathVariables(WarcraftContext context) {
        return Maps.of("season", toSnakeCase(context.getAssetSet().name()));
    }

    public Texture getTerrainTexture() {
        return getTexture("terrain");
    }
}
