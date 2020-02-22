/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.gather;

import com.evilbird.engine.action.framework.AbstractAction;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.unit.combatant.gatherer.Gatherer;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Idle;

/**
 * Instances of this class stop a given {@link GameObject} from gathering, retaining
 * resources from the partially completed gathering process.
 *
 * @author Blair Butterworth
 */
public class GatherCancel extends AbstractAction
{
    private transient GatherEvents events;

    @Inject
    public GatherCancel(GatherEvents events) {
        this.events = events;
    }

    @Override
    public boolean act(float delta) {
        Gatherer gatherer = (Gatherer) getSubject();
        gatherer.setAnimation(Idle);
        events.notifyGatherCancelled(gatherer);
        return ActionComplete;
    }
}
