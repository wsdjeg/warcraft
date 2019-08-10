/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common.create;

import com.evilbird.engine.events.Event;
import com.evilbird.engine.item.Item;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;

/**
 * Instances of this {@link Event} are produced when a new {@link Item} is
 * created.
 *
 * @author Blair Butterworth
 */
public class CreateEvent implements Event
{
    private Item subject;

    /**
     * constructs a new instance of this class given an {@link Item} that has
     * just been created.
     *
     * @param newItem an {@code Item}. This parameter cannot be {@code null}.
     */
    public CreateEvent(Item newItem) {
        Objects.requireNonNull(newItem);
        this.subject = newItem;
    }

    @Override
    public Item getSubject() {
        return subject;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("subject", subject.getIdentifier())
            .toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        CreateEvent that = (CreateEvent)obj;
        return new EqualsBuilder()
            .append(subject, that.subject)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(subject)
            .toHashCode();
    }
}
