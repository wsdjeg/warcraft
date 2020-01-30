/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ainew;

import com.badlogic.gdx.ai.btree.branch.Parallel;
import com.evilbird.warcraft.behaviour.ainew.wander.WanderTree;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * A {@link PlayerBehaviour} specialization specifying the behaviour of neutral
 * players.
 *
 * @author Blair Butterworth
 */
public class NeutralBehaviour extends PlayerBehaviour
{
    @Inject
    public NeutralBehaviour(Provider<WanderTree> wanderFactory) {
        super(new Parallel<>(
            wanderFactory.get(),
            wanderFactory.get(),
            wanderFactory.get(),
            wanderFactory.get()
        ));
    }
}
