/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.train;

import com.evilbird.engine.events.Event;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.building.Building;

/**
 * Instances of this {@link Event} are generated when a unit is trained.
 *
 * @author Blair Butterworth
 */
public class TrainEvent implements Event
{
    private Building building;
    private TrainStatus status;

    public TrainEvent(Building building, TrainStatus status) {
        this.building = building;
        this.status = status;
    }

    public Building getBuilding() {
        return building;
    }

    public TrainStatus getStatus() {
        return status;
    }

    @Override
    public Item getSubject() {
        return building;
    }

    public boolean isTraining() {
        return status == TrainStatus.Started;
    }
}