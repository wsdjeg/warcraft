/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.combatant.human;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.item.unit.combatant.Combatant;
import com.evilbird.warcraft.item.unit.combatant.CombatantFactoryBase;
import com.evilbird.warcraft.item.unit.combatant.RangedCombatant;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.item.WarcraftItemConstants.tiles;
import static com.evilbird.warcraft.item.common.movement.MovementCapability.Land;
import static com.evilbird.warcraft.item.unit.UnitType.Mage;

/**
 * Instances of this factory create Mages, Human spell casting
 * {@link Combatant Combatants}.
 *
 * @author Blair Butterworth
 */
public class MageFactory extends CombatantFactoryBase
{
    @Inject
    public MageFactory(Device device) {
        this(device.getAssetStorage());
    }

    public MageFactory(AssetManager manager) {
        super(manager, Mage);
    }

    @Override
    public Combatant get(Identifier type) {
        RangedCombatant result = builder.newRangedCombatant();
        result.setAttackSpeed(1.5f);
        result.setDefence(0);
        result.setDamageMinimum(2);
        result.setDamageMaximum(9);
        result.setHealth(60);
        result.setHealthMaximum(60);
        result.setIdentifier(objectIdentifier("Mage", result));
        result.setLevel(1);
        result.setName("Mage");
        result.setMovementSpeed(8 * 10);
        result.setMovementCapability(Land);
        result.setRange(tiles(2));
        result.setSight(tiles(9));
        result.setType(Mage);
        return result;
    }
}