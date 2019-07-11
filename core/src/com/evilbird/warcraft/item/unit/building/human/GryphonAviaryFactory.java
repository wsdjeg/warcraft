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
import static com.evilbird.warcraft.item.unit.UnitType.GryphonAviary;

/**
 * Instances of this class create Gryphon Aviaries, allowing for the creation
 * of Gryphon Riders.
 *
 * @author Blair Butterworth
 */
public class GryphonAviaryFactory implements AssetProvider<Item>
{
    private static final GridPoint2 ICON = new GridPoint2(92, 532);

    private BuildingAssets assets;
    private BuildingBuilder builder;

    @Inject
    public GryphonAviaryFactory(Device device) {
        this(device.getAssetStorage());
    }

    public GryphonAviaryFactory(AssetManager manager) {
        this.assets = new BuildingAssets(manager, GryphonAviary, ICON);
        this.builder = new BuildingBuilder(assets);
    }

    @Override
    public void load() {
        assets.load();
    }

    @Override
    public Item get() {
        Building result = builder.build();
        result.setDefence(20);
        result.setHealth(500);
        result.setHealthMaximum(500);
        result.setIdentifier(objectIdentifier("GryphonAviary", result));
        result.setName("Gryphon Aviary");
        result.setSight(tiles(3));
        result.setType(GryphonAviary);
        return result;
    }
}