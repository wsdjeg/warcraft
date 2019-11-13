/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell.attack;

import com.evilbird.engine.object.GameObjectFactory;
import com.evilbird.warcraft.action.common.remove.DeathAction;
import com.evilbird.warcraft.action.spell.SpellAction;
import com.evilbird.warcraft.object.effect.EffectType;
import com.evilbird.warcraft.object.unit.Unit;

import javax.inject.Inject;

import static com.evilbird.engine.object.utility.GameObjectOperations.assignIfAbsent;
import static com.evilbird.warcraft.object.common.spell.Spell.Exorcism;

/**
 * A spell that degrades the health of a given game object. Damage is dealt
 * instantaneously.
 *
 * @author Blair Butterworth
 */
public class ExorcismSpell extends SpellAction
{
    private transient DeathAction death;

    @Inject
    public ExorcismSpell(GameObjectFactory factory, DeathAction death) {
        super(Exorcism, EffectType.Exorcism, factory);
        this.death = death;
    }

    @Override
    protected void initialize() {
        super.initialize();

        Unit target = (Unit)getTarget();
        target.setHealth(Math.max(0, target.getHealth() - Exorcism.getValue()));

        if (target.getHealth() == 0) {
            assignIfAbsent(target, death);
        }
    }
}
