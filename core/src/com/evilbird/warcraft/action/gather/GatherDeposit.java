/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.gather;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionResult;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.common.time.GameTimer;
import com.evilbird.warcraft.action.common.exclusion.ItemExclusion;
import com.evilbird.warcraft.action.common.transfer.ResourceTransfer;
import com.evilbird.warcraft.data.resource.ResourceQuantity;
import com.evilbird.warcraft.data.resource.ResourceType;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.unit.building.Building;
import com.evilbird.warcraft.object.unit.combatant.gatherer.Gatherer;

import javax.inject.Inject;

import static com.evilbird.warcraft.object.common.query.UnitOperations.getPlayer;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Idle;
import static com.evilbird.warcraft.object.unit.UnitAnimation.IdleBasic;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Move;
import static com.evilbird.warcraft.object.unit.UnitAnimation.MoveBasic;

/**
 * An {@link Action} that stores resources in a repository.
 *
 * @author Blair Butterworth
 */
public class GatherDeposit extends BasicAction
{
    private static final float DEPOSIT_DURATION = 5;

    private transient GameTimer timer;
    private transient GatherEvents events;
    private transient ResourceType resource;
    private transient ItemExclusion exclusion;
    private transient ResourceTransfer resources;

    @Inject
    public GatherDeposit(GatherEvents events, ItemExclusion exclusion, ResourceTransfer resources) {
        this.events = events;
        this.exclusion = exclusion;
        this.resources = resources;
    }

    @Override
    public ActionResult act(float time) {
        if (! initialized()) {
            return initialize();
        }
        if (! loaded()) {
            return load();
        }
        if (timer.advance(time)) {
            return complete();
        }
        return update();
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

    private boolean initialized() {
        Gatherer gatherer = (Gatherer) getSubject();
        return gatherer.isGathering();
    }

    private ActionResult initialize() {
        Gatherer gatherer = (Gatherer) getSubject();
        gatherer.setGathererProgress(0);
        exclusion.disable(gatherer);

        Building depot = (Building)getTarget();
        ResourceQuantity quantity = new ResourceQuantity(resource, getGatherCapacity(gatherer));

        events.notifyDepositStarted(gatherer, depot, quantity);
        return ActionResult.Incomplete;
    }

    protected boolean loaded() {
        return timer != null;
    }

    protected ActionResult load() {
        Gatherer gatherer = (Gatherer) getSubject();
        timer = new GameTimer(DEPOSIT_DURATION);
        timer.advance(gatherer.getGathererProgress() * timer.duration());
        return ActionResult.Incomplete;
    }

    private ActionResult update() {
        Gatherer gatherer = (Gatherer) getSubject();
        gatherer.setGathererProgress(timer.completion());
        return ActionResult.Incomplete;
    }

    private ActionResult complete() {
        Gatherer gatherer = (Gatherer) getSubject();
        gatherer.setGathererProgress(1);
        gatherer.setAnimationAlias(IdleBasic, Idle);
        gatherer.setAnimationAlias(MoveBasic, Move);
        exclusion.restore(gatherer);

        Building depot = (Building)getTarget();
        Player player = getPlayer(depot);
        ResourceQuantity quantity = new ResourceQuantity(resource, getGatherCapacity(gatherer));
        resources.transfer(gatherer, player, quantity);

        events.notifyDepositComplete(gatherer, depot, quantity);
        return ActionResult.Complete;
    }

    private float getGatherCapacity(Gatherer gatherer) {
        switch (resource) {
            case Gold: return gatherer.getGoldCapacity();
            case Oil: return gatherer.getOilCapacity();
            case Wood: return gatherer.getWoodCapacity();
            default: throw new UnsupportedOperationException();
        }
    }
}
