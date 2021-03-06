/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.operation.reorient;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.utility.GameObjectOperations;
import com.evilbird.warcraft.behaviour.ai.operation.player.PlayerData;
import com.evilbird.warcraft.object.common.capability.MovableObject;
import com.evilbird.warcraft.object.common.query.UnitOperations;
import com.evilbird.warcraft.object.data.player.Player;
import org.apache.commons.lang3.RandomUtils;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.math.MathUtils.random;

/**
 * A {@link LeafTask} implementation that randomly re-orients idle units.
 *
 * @author Blair Butterworth
 */
public class ReorientTask extends LeafTask<PlayerData>
{
    private static final int REORIENT_MIN = 2;
    private static final int REORIENT_MAX = 4;

    @Inject
    public ReorientTask() {
    }

    @Override
    public Status execute() {
        List<GameObject> movables = getMovableObjects();
        for (int i = 0; i < RandomUtils.nextInt(REORIENT_MIN, REORIENT_MAX); i++) {
            MovableObject target = (MovableObject)movables.get(random.nextInt(movables.size()));
            if (UnitOperations.isAlive(target) && GameObjectOperations.isIdle(target)) {
                target.setDirection(getRandomDirection());
            }
        }
        return Status.SUCCEEDED;
    }

    private List<GameObject> getMovableObjects() {
        PlayerData data = getObject();
        Player player = data.getPlayer();
        return new ArrayList<>(player.findAll(UnitOperations::isMovable));
    }

    private Vector2 getRandomDirection() {
        Vector2 result = new Vector2(1, 1);
        result.rotate(random.nextInt(360));
        return result;
    }

    @Override
    protected Task<PlayerData> copyTo(Task<PlayerData> task) {
        return task;
    }
}
