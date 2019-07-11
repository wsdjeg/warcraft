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
import static com.evilbird.warcraft.item.unit.UnitType.Refinery;

/**
 * Instances of this class create Human Refinery, a building that provides
 * access to advanced ships.
 *
 * @author Blair Butterworth
 */
public class RefineryFactory implements GameFactory<Building>
{
    private BuildingAssets assets;
    private BuildingBuilder builder;

    @Inject
    public RefineryFactory(Device device) {
        this(device.getAssetStorage());
    }

    public RefineryFactory(AssetManager manager) {
        this.assets = new BuildingAssets(manager, Refinery);
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
        result.setHealth(600);
        result.setHealthMaximum(600);
        result.setIdentifier(objectIdentifier("Refinery", result));
        result.setName("Refinery");
        result.setSight(tiles(3));
        result.setType(Refinery);
        return result;
    }
}
