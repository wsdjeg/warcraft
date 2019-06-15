/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.layer.wall;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.collection.BitMatrix;
import com.evilbird.engine.common.inject.IdentifiedAssetProvider;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.item.layer.LayerGroupStyle;
import com.evilbird.warcraft.item.layer.LayerIdentifier;
import com.evilbird.warcraft.item.layer.LayerUtils;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

import static com.evilbird.warcraft.item.layer.LayerUtils.cell;

/**
 * Instances of this factory create {@link Wall} instances, loading the
 * appropriate assets to display the wall and its cells in different
 * states.
 *
 * @author Blair Butterworth
 */
public class WallFactory implements IdentifiedAssetProvider<Wall>
{
    public static final String TERRAIN = "data/textures/neutral/winter/terrain.png";

    private AssetManager assets;

    @Inject
    public WallFactory(Device device) {
        this(device.getAssetStorage());
    }

    public WallFactory(AssetManager assets) {
        this.assets = assets;
    }

    @Override
    public void load() {
        assets.load(TERRAIN, Texture.class);
    }

    @Override
    public Wall get(Identifier identifier) {
        Validate.isInstanceOf(LayerIdentifier.class, identifier);
        LayerIdentifier layerIdentifier = (LayerIdentifier)identifier;

        Wall forest = new Wall(getSkin());
        forest.setIdentifier(layerIdentifier);
        forest.setType(layerIdentifier.getType());
        forest.setLayer(LayerUtils.getLayer(layerIdentifier));
        forest.setVisible(true);
        forest.setTouchable(Touchable.childrenOnly);
        return forest;
    }

    private Skin getSkin() {
        Skin skin = new Skin();
        skin.add("default", getStyle());
        return skin;
    }

    private LayerGroupStyle getStyle() {
        Texture terrain = assets.get(TERRAIN, Texture.class);
        LayerGroupStyle forestStyle = new LayerGroupStyle();
        forestStyle.empty = cell(terrain, 256, 128, 32, 32);
        forestStyle.patterns = getCellStyles(terrain);
        return forestStyle;
    }

    private Map<BitMatrix, TiledMapTileLayer.Cell> getCellStyles(Texture texture) {
        Map<BitMatrix, TiledMapTileLayer.Cell> styles = new HashMap<>();
        return styles;
    }
}