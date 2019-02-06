/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.gather;

import com.evilbird.engine.action.ActionContext;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.action.common.*;
import com.evilbird.engine.action.framework.*;
import com.evilbird.engine.action.framework.duration.ActionDuration;
import com.evilbird.engine.action.framework.duration.TimeDuration;
import com.evilbird.engine.common.function.Function;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemGroup;
import com.evilbird.engine.item.specialized.animated.Animated;
import com.evilbird.warcraft.action.ActionProvider;
import com.evilbird.warcraft.action.common.AnimatedMoveAction;
import com.evilbird.warcraft.action.common.AnimationAliasAction;
import com.evilbird.warcraft.action.common.ResourceTransferAction;
import com.evilbird.warcraft.item.common.capability.Destroyable;
import com.evilbird.warcraft.item.common.capability.ResourceContainer;
import com.evilbird.warcraft.item.data.DataType;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.resource.ResourceType;

import static com.evilbird.engine.item.ItemOperations.findAncestorByType;
import static com.evilbird.engine.item.ItemSuppliers.*;
import static com.evilbird.warcraft.item.unit.UnitAnimation.getGatherAnimation;
import static com.evilbird.warcraft.item.unit.UnitType.TownHall;

/**
 * Instances of this class provide common gathering functionality.
 *
 * @author Blair Butterworth
 */
//TODO: Cope with the resource dieing during action
//TODO: Cope with leaving mid gather and returning to Depot
//TODO: Cope with other types of depot. E.g., lumbermill
//TODO: Cope with multiple gathers using single resource - trees
//TODO: Cope with no more resources
//TODO: Only choose next resource if it can be accessed - trees on the edge of forests, not within
public abstract class Gather implements ActionProvider
{
    @Override
    public Action get(ActionIdentifier action, ActionContext context) {
        Item gatherer = context.getItem();
        Item resource = context.getTarget();
        Action gather = gather(gatherer, resource);
        Action gatherContinually = new RepeatedAction(gather);
        return new ReplacementAction(gatherer, gatherContinually);
    }

    protected Action gather(Item gatherer, Item resource) {
        ItemGroup player = (ItemGroup)findAncestorByType(gatherer, DataType.Player);
        Action obtain = new ReferenceAction(findClosest(resource), new ObtainAction(gatherer));
        Action deposit = new ReferenceAction(findClosest(player, TownHall, gatherer), new DepositAction(gatherer, player));
        Action optionallyDeposit = new OptionalAction(hasResources((ResourceContainer)gatherer, getResourceType()), deposit);
        return new SequenceAction(optionallyDeposit, obtain);
    }

    private class ObtainAction implements Function<Item, Action> {
        private Item gatherer;

        public ObtainAction(Item gatherer){
            this.gatherer = gatherer;
        }

        public Action apply(Item resource) {
            return obtain(gatherer, resource);
        }
    }

    protected Action obtain(Item gatherer, Item resource) {
        Action move = new AnimatedMoveAction(gatherer, resource);
        Action preObtain = preObtainAction(gatherer, resource);
        Action obtain = obtainAction(gatherer, resource);
        Action postObtain = postObtainAction(gatherer, resource);
        return new SequenceAction(move, preObtain, obtain, postObtain);
    }

    protected Action obtainAction(Item gatherer, Item resource) {
        Action transferDelay = new DelayedAction(getGatherSpeed(gatherer), isAlive((Destroyable)resource));
        Action transferFrom = new ResourceTransferAction((ResourceContainer)resource, getResourceType(), getGatherCapacity(gatherer) * -1);
        Action transferTo = new ResourceTransferAction((ResourceContainer)gatherer, getResourceType(), getGatherCapacity(gatherer));
        Action transferAction = new ParallelAction(transferFrom, transferTo);
        return new SequenceAction(transferDelay, transferAction);
    }

    protected Action preObtainAction(Item gatherer, Item resource) {
        Action gathererAnimation = new AnimateAction((Animated)gatherer, getGatherAnimation(getResourceType()));
        Action resourceAnimation = new AnimateAction((Animated)resource, UnitAnimation.Gathering);
        return new ParallelAction(gathererAnimation, resourceAnimation);
    }

    protected Action postObtainAction(Item gatherer, Item resource) {
        Action gatherMovement = new AnimationAliasAction((Animated)gatherer, UnitAnimation.Move, UnitAnimation.getMoveAnimation(getResourceType()));
        Action gathererIdle = new AnimationAliasAction((Animated)gatherer, UnitAnimation.Idle, UnitAnimation.getIdleAnimation(getResourceType()));

        Action deselect = new SelectAction(resource, false);
        Action disable = new DisableAction(resource, false);
        Action deadAnimation = new AnimateAction((Animated)resource, UnitAnimation.Dead);
        Action deadAction = new ParallelAction(deselect, disable, deadAnimation);
        Action aliveAction = new AnimateAction((Animated)resource, UnitAnimation.Idle);
        Action resourceAnimation = new BranchAction(hasResources((ResourceContainer)resource, getResourceType()), aliveAction, deadAction);

        return new ParallelAction(gatherMovement, gathererIdle, resourceAnimation);
    }

    private class DepositAction implements Function<Item, Action> {
        private Item gatherer;
        private Item player;

        public DepositAction(Item gatherer, Item player){
            this.gatherer = gatherer;
            this.player = player;
        }

        public Action apply(Item depot) {
            return deposit(gatherer, depot, player);
        }
    }

    protected Action deposit(Item gatherer, Item depot, Item player) {
        Action move = new AnimatedMoveAction(gatherer, depot);
        Action preDeposit = preDepositAction(gatherer);
        Action deposit = depositAction(gatherer, depot, player);
        Action postDeposit = postDepositAction(gatherer);
        return new SequenceAction(move, preDeposit, deposit, postDeposit);
    }

    protected Action depositAction(Item gatherer, Item depot, Item player) {
        Action transferFrom = new ResourceTransferAction((ResourceContainer)gatherer, getResourceType(), getGatherCapacity(gatherer) * -1);
        Action transferTo = new ResourceTransferAction((ResourceContainer)player, getResourceType(), getGatherCapacity(gatherer));
        Action transfer = new ParallelAction(transferFrom, transferTo);
        Action transferDelay = new DelayedAction(new TimeDuration(5f), isAlive((Destroyable)depot));
        return new SequenceAction(transfer, transferDelay);
    }

    protected Action preDepositAction(Item gatherer) {
        Action deselect = new SelectAction(gatherer, false);
        Action disable = new DisableAction(gatherer, false);
        Action hide = new VisibleAction(gatherer, false);
        return new ParallelAction(deselect, disable, hide);
    }

    private Action postDepositAction(Item gatherer) {
        Action resetMove = new AnimationAliasAction((Animated)gatherer, UnitAnimation.Move, UnitAnimation.MoveBasic);
        Action animate = new AnimationAliasAction((Animated)gatherer, UnitAnimation.Idle, UnitAnimation.IdleBasic);
        Action enable = new DisableAction(gatherer, true);
        Action show = new VisibleAction(gatherer, true);
        return new ParallelAction(resetMove, animate, enable, show);
    }

    protected abstract ResourceType getResourceType();

    protected abstract float getGatherCapacity(Item gatherer);

    protected abstract ActionDuration getGatherSpeed(Item gatherer);
}