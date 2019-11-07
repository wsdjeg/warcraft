/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell.buff;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.events.Events;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.action.spell.SpellSelect;
import com.evilbird.warcraft.item.common.query.UnitOperations;
import com.evilbird.warcraft.item.common.spell.Spell;

import javax.inject.Inject;
import java.util.function.Predicate;

import static com.evilbird.engine.common.function.Predicates.both;
import static com.evilbird.engine.common.function.Predicates.not;

/**
 * An {@link Action} that highlights combatants belonging to the user to be the
 * recipient of the death coil spell.
 *
 * @author Blair Butterworth
 */
public class DeathCoilSelect extends SpellSelect
{
    private static final Predicate<Item> CONDITION =
        both(not(UnitOperations::isControllable), UnitOperations::isCombatant);

    @Inject
    public DeathCoilSelect(Events events) {
        super(events, Spell.DeathCoil, CONDITION);
    }
}