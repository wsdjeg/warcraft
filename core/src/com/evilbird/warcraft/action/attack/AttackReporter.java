/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.attack;

import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.spatial.ItemNode;
import com.evilbird.warcraft.action.move.MoveEvent;
import com.evilbird.warcraft.action.move.MoveObserver;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

import javax.inject.Inject;

/**
 * Instances of this class generate {@link AttackEvent AttackEvents} based on
 * the operation of attack actions.
 *
 * @author Blair Butterworth
 */
public class AttackReporter implements AttackObserver, MoveObserver
{
    private EventQueue events;

    @Inject
    public AttackReporter(EventQueue events) {
        this.events = events;
    }

    @Override
    public void onAttack(Combatant attacker, Item target) {
        events.add(new AttackEvent(attacker, target));
    }

    @Override
    public void onMove(Item subject, ItemNode location) {
        events.add(new MoveEvent(subject, location));
    }
}
