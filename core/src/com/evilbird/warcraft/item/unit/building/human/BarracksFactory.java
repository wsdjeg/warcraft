/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.building.human;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.game.GameFactory;
import com.evilbird.warcraft.item.unit.building.Building;
import com.evilbird.warcraft.item.unit.building.BuildingAssets;
import com.evilbird.warcraft.item.unit.building.BuildingBuilder;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.item.WarcraftItemConstants.tiles;
import static com.evilbird.warcraft.item.unit.UnitType.Barracks;

/**
 * Instances of this class create {@link Building Barrack's}, loading the
 * necessary assets and defining the appropriate attributes.
 *
 * @author Blair Butterworth
 */
public class BarracksFactory implements GameFactory<Building>
{
    private BuildingAssets assets;
    private BuildingBuilder builder;

    @Inject
    public BarracksFactory(Device device) {
        this(device.getAssetStorage());
    }

    public BarracksFactory(AssetManager manager) {
        this.assets = new BuildingAssets(manager, Barracks);
        this.builder = new BuildingBuilder(assets);
    }

    @Override
    public void load(Identifier context) {
        assets.load();
    }

    @Override
    public void unload(Identifier context) {
    }

    @Override
    public Building get(Identifier type) {
        Building result = builder.build();
        result.setDefence(20);
        result.setHealth(800);
        result.setHealthMaximum(800);
        result.setIdentifier(objectIdentifier("Barracks", result));
        result.setName("Barracks");
        result.setSight(tiles(3));
        result.setType(Barracks);
        return result;
    }
}
