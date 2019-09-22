/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.gather;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.common.lang.Destroyable;
import com.evilbird.engine.common.time.GameTimer;
import com.evilbird.warcraft.action.common.transfer.ResourceTransfer;
import com.evilbird.warcraft.action.death.DeathAction;
import com.evilbird.warcraft.item.common.resource.ResourceContainer;
import com.evilbird.warcraft.item.common.resource.ResourceQuantity;
import com.evilbird.warcraft.item.common.resource.ResourceType;
import com.evilbird.warcraft.item.unit.gatherer.Gatherer;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;
import static com.evilbird.engine.action.ActionConstants.ActionIncomplete;

/**
 * An {@link Action} that obtains resources from a resource.
 *
 * @author Blair Butterworth
 */
class GatherObtain extends BasicAction
{
    protected transient GameTimer timer;
    protected transient DeathAction death;
    protected transient GatherEvents events;
    protected transient ResourceType resource;
    protected transient ResourceTransfer transferer;

    public GatherObtain(GatherEvents events, DeathAction death, ResourceTransfer transferer) {
        this.events = events;
        this.death = death;
        this.transferer = transferer;
    }

    @Override
    public boolean act(float time) {
        if (! initialized()) {
            return initialize();
        }
        if (! loaded()) {
            return load();
        }
        if (timer.advance(time)) {
            return complete();
        }
        return update(time);
    }

    @Override
    public void reset() {
        super.reset();
        timer = null;
    }

    @Override
    public void restart() {
        super.restart();
        timer = null;
    }

    public void setResource(ResourceType resource) {
        this.resource = resource;
    }

    protected boolean initialized() {
        Gatherer gatherer = (Gatherer)getItem();
        return gatherer.isGathering() && !gatherer.hasOtherResource(resource);
    }

    protected boolean initialize() {
        Gatherer gatherer = (Gatherer)getItem();
        gatherer.clearResources();
        gatherer.setGathererProgress(0);

        ResourceQuantity quantity = new ResourceQuantity(resource, gatherer.getGatherCapacity(resource));
        events.notifyObtainStarted(gatherer, getTarget(), quantity);

        return ActionIncomplete;
    }

    protected boolean loaded() {
        return timer != null;
    }

    protected boolean load() {
        Gatherer gatherer = (Gatherer)getItem();
        timer = new GameTimer(gatherer.getGatherDuration(resource));
        timer.advance(gatherer.getGathererProgress() * timer.duration());
        return ActionIncomplete;
    }

    protected boolean update(float time) {
        Gatherer gatherer = (Gatherer)getItem();
        gatherer.setGathererProgress(timer.completion());
        return ActionIncomplete;
    }

    protected boolean complete() {
        Gatherer gatherer = (Gatherer)getItem();
        gatherer.setGathererProgress(1);

        ResourceQuantity quantity = new ResourceQuantity(resource, gatherer.getGatherCapacity(resource));
        ResourceContainer container = (ResourceContainer)getTarget();
        transferer.transfer(container, gatherer, quantity);
        resourceEmpty(container);

        events.notifyObtainComplete(gatherer, container, quantity);
        return ActionComplete;
    }

    protected void resourceEmpty(ResourceContainer container) {
        if (container instanceof Destroyable && container.getResource(resource) == 0) {
            death.setItem(container);
            container.addAction(death);
        }
    }
}