/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.combatant.orc;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.item.unit.combatant.Combatant;
import com.evilbird.warcraft.item.unit.combatant.CombatantFactoryBase;
import com.evilbird.warcraft.item.unit.combatant.RangedCombatant;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.item.WarcraftItemConstants.tiles;
import static com.evilbird.warcraft.item.common.movement.MovementCapability.Air;
import static com.evilbird.warcraft.item.projectile.ProjectileType.Arrow;
import static com.evilbird.warcraft.item.unit.UnitType.Dragon;

/**
 * Instances of this factory create Dragon, Orcish heavy flying
 * {@link Combatant Combatants}.
 *
 * @author Blair Butterworth
 */
public class DragonFactory extends CombatantFactoryBase
{
    @Inject
    public DragonFactory(Device device) {
        this(device.getAssetStorage());
    }

    public DragonFactory(AssetManager manager) {
        super(manager, Dragon);
    }

    @Override
    public Combatant get(Identifier type) {
        RangedCombatant result = builder.newRangedCombatant();
        result.setAttackSpeed(1.5f);
        result.setDefence(0);
        result.setDamageMinimum(3);
        result.setDamageMaximum(9);
        result.setHealth(40);
        result.setHealthMaximum(40);
        result.setIdentifier(objectIdentifier("Dragon", result));
        result.setLevel(1);
        result.setName("Dragon");
        result.setMovementSpeed(8 * 10);
        result.setMovementCapability(Air);
        result.setRange(tiles(4));
        result.setSight(tiles(5));
        result.setType(Dragon);
        result.setProjectileType(Arrow);
        return result;
    }
}