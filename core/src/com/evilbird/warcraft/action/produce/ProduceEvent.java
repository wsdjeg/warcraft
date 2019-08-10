/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.produce;

import com.evilbird.engine.events.Event;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.building.Building;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Instances of this {@link Event} are generated when a unit is trained.
 *
 * @author Blair Butterworth
 */
public class ProduceEvent implements Event
{
    private Building building;
    private ProduceStatus status;

    public ProduceEvent(Building building, ProduceStatus status) {
        this.building = building;
        this.status = status;
    }

    public Building getBuilding() {
        return building;
    }

    public ProduceStatus getStatus() {
        return status;
    }

    @Override
    public Item getSubject() {
        return building;
    }

    public boolean isTraining() {
        return status == ProduceStatus.Started;
    }

    public boolean isComplete() {
        return status == ProduceStatus.Complete;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("building", building.getIdentifier())
            .append("status", status)
            .toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        ProduceEvent that = (ProduceEvent)obj;
        return new EqualsBuilder()
            .append(building, that.building)
            .append(status, that.status)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(building)
            .append(status)
            .toHashCode();
    }
}
