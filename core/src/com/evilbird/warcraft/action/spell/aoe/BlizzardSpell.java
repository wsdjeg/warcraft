/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell.aoe;

import com.evilbird.engine.object.GameObjectFactory;
import com.evilbird.warcraft.object.common.spell.Spell;
import com.evilbird.warcraft.object.effect.EffectType;
import com.evilbird.warcraft.object.unit.UnitType;

import javax.inject.Inject;

/**
 * A spell that deals damage to all game objects in a given area.
 *
 * @author Blair Butterworth
 */
public class BlizzardSpell extends AoeSpellAction
{
    @Inject
    public BlizzardSpell(GameObjectFactory factory, AoeSpellCancel cancel) {
        super(Spell.Blizzard, EffectType.Spell, UnitType.Blizzard, factory, cancel);
    }
}
