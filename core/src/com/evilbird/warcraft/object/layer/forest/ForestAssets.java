/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.layer.forest;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.common.assets.AssetBundle;
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.warcraft.state.WarcraftContext;

import java.util.Map;

import static com.evilbird.engine.common.text.CaseUtils.toSnakeCase;

/**
 * Provides access to the assets that are required to display a {@link Forest}.
 *
 * @author Blair Butterworth
 */
public class ForestAssets extends AssetBundle
{
    public ForestAssets(AssetManager manager, WarcraftContext context) {
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