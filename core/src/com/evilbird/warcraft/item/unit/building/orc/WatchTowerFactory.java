/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.building.orc;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.building.Building;
import com.evilbird.warcraft.item.unit.building.BuildingAssets;
import com.evilbird.warcraft.item.unit.building.BuildingBuilder;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.item.WarcraftItemConstants.tiles;
import static com.evilbird.warcraft.item.unit.UnitType.WatchTower;

/**
 * Instances of this class create {@link Building Watch Towers}, loading the
 * necessary assets and defining the appropriate attributes.
 *
 * @author Blair Butterworth
 */
public class WatchTowerFactory implements AssetProvider<Item>
{
    private static final GridPoint2 ICON = new GridPoint2(46, 456);
    private static final GridPoint2 SIZE = new GridPoint2(64, 64);

    private BuildingAssets assets;
    private BuildingBuilder builder;

    @Inject
    public WatchTowerFactory(Device device) {
        this(device.getAssetStorage());
    }

    public WatchTowerFactory(AssetManager manager) {
        this.assets = new BuildingAssets(manager, WatchTower, ICON, SIZE);
        this.builder = new BuildingBuilder(assets);
    }

    @Override
    public void load() {
        assets.load();
    }

    @Override
    public Item get() {
        Building result = builder.build();
        result.setHealth(100);
        result.setHealthMaximum(100);
        result.setIdentifier(objectIdentifier("WatchTower", result));
        result.setName("Watch Tower");
        result.setSight(tiles(5));
        result.setType(WatchTower);
        return result;
    }
}
