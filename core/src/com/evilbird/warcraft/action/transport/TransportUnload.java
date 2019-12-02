/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.transport;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.warcraft.action.common.exclusion.ItemExclusion;
import com.evilbird.warcraft.action.move.MoveAdjacent;
import com.evilbird.warcraft.object.common.capability.MovableObject;
import com.evilbird.warcraft.object.unit.Unit;
import com.evilbird.warcraft.object.unit.combatant.naval.Transport;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;

/**
 * Represents an {@link Action} that moves a disembarkee from a vessel.
 *
 * @author Blair Butterworth
 */
public class TransportUnload extends BasicAction
{
    private transient ItemExclusion exclusion;
    private transient MoveAdjacent movement;

    @Inject
    public TransportUnload(ItemExclusion exclusion, MoveAdjacent movement) {
        this.exclusion = exclusion;
        this.movement = movement;
    }

    @Override
    public boolean act(float delta) {
        Transport vessel = (Transport)getSubject();
        for (Unit passenger: vessel.getPassengers()) {
            if (movement.reposition((MovableObject)passenger, vessel)) {
                vessel.removePassenger(passenger);
                exclusion.restore(passenger);
            }
        }
        if (vessel.hasPassengers()) {
            setError(new TransportFailed("Unable to offload all units"));
        }
        return ActionComplete;
    }
}
