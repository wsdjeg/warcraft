/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.attack;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.CompositeAction;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.common.capability.OffensiveObject;
import com.evilbird.warcraft.object.common.capability.PerishableObject;
import com.evilbird.warcraft.object.unit.UnitAnimation;
import org.apache.commons.lang3.Validate;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;
import static com.evilbird.engine.action.ActionConstants.ActionIncomplete;
import static com.evilbird.engine.object.utility.GameObjectOperations.assignIfAbsent;
import static com.evilbird.warcraft.object.common.query.UnitOperations.inRange;

/**
 * An {@link Action} that causes a given {@link OffensiveObject} to attack a
 * {@link PerishableObject}, after first moving within attack range, if
 * applicable.
 *
 * @author Blair Butterworth
 */
public abstract class AttackSequence extends CompositeAction
{
    private transient Action move;
    private transient Action attack;
    private transient Action death;
    private transient Action current;
    private transient AttackEvents events;

    public AttackSequence(AttackEvents events, Action move, Action attack, Action death) {
        super(move, attack, death);
        this.events = events;
        this.move = move;
        this.attack = attack;
        this.death = death;
    }

    @Override
    public boolean act(float time) {
        OffensiveObject attacker = (OffensiveObject)getSubject();
        PerishableObject target = (PerishableObject)getTarget();
        return act(time, attacker, target);
    }

    private boolean act(float time, OffensiveObject attacker, PerishableObject target) {
        if (attackFailed(attacker, target)) {
            return failed(attacker, target);
        }
        if (moveRequired(attacker, target)) {
            return move(time, attacker, target);
        }
        if (attackRequired(attacker, target)) {
            return attack(time, attacker, target);
        }
        if (killRequired(attacker, target)) {
            return kill(attacker, target);
        }
        return ActionComplete;
    }

    @Override
    public void setSubject(GameObject subject) {
        Validate.isInstanceOf(OffensiveObject.class, subject);
        super.setSubject(subject);
    }

    @Override
    public void setTarget(GameObject target) {
        Validate.isInstanceOf(PerishableObject.class, target);
        super.setTarget(target);
    }

    protected boolean attackFailed(OffensiveObject attacker, PerishableObject target) {
        return !target.getVisible() || !target.isAttackable() || !attacker.isAlive();
    }

    protected boolean failed(OffensiveObject attacker, PerishableObject target) {
        resetAttacker(attacker);
        events.attackFailed(attacker, target);
        return ActionComplete;
    }

    protected boolean moveRequired(OffensiveObject attacker, PerishableObject target) {
       return current == move || !inRange(attacker, target);
    }

    protected boolean move(float time, OffensiveObject attacker, PerishableObject target) {
        if (current == attack) {
            events.attackStopped(attacker, target);
        }
        if (current != move) {
            current = move;
            current.restart();
            current.setError(null);
        }
        if (current.act(time)) {
            current = null;
            moveComplete(attacker, target);
        }
        return move.hasError() ? ActionComplete : ActionIncomplete;
    }

    protected void moveComplete(OffensiveObject attacker, PerishableObject target) {
        resetAttacker(attacker);
        if (move.hasError()) {
            events.attackFailed(attacker, target);
        }
    }

    protected boolean attackRequired(OffensiveObject attacker, PerishableObject target) {
        return target.getHealth() > 0;
    }

    protected boolean attack(float time, OffensiveObject attacker, PerishableObject target) {
        if (current != attack) {
            events.attackStarted(attacker, target);
            current = attack;
            current.restart();
            current.setError(null);
        }
        if (current.act(time)) {
            current = null;
            attackComplete(attacker, target);
        }
        return attack.hasError() ? ActionComplete : ActionIncomplete;
    }

    protected void attackComplete(OffensiveObject attacker, PerishableObject target) {
        resetAttacker(attacker);
        if (attack.hasError()) {
            events.attackFailed(attacker, target);
        } else {
            events.attackComplete(attacker, target);
        }
    }

    protected boolean killRequired(OffensiveObject attacker, PerishableObject target) {
        return target.getHealth() == 0;
    }

    protected boolean kill(OffensiveObject attacker, PerishableObject target) {
        resetAttacker(attacker);
        assignIfAbsent(target, death);
        return ActionComplete;
    }

    protected void resetAttacker(OffensiveObject attacker) {
        attacker.setAnimation(UnitAnimation.Idle);
    }
}
