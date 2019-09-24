/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.attack;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.common.lang.Alignment;
import com.evilbird.engine.common.lang.Destroyable;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.item.ItemGroup;
import com.evilbird.warcraft.item.projectile.Projectile;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.UnitSound;
import com.evilbird.warcraft.item.unit.combatant.RangedCombatant;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionConstants.ActionIncomplete;
import static com.evilbird.warcraft.action.attack.AttackDamage.getDamagedHealth;
import static com.evilbird.warcraft.item.common.query.UnitOperations.isShip;
import static com.evilbird.warcraft.item.common.query.UnitOperations.reorient;

/**
 * Modifies the state of a {@link RangedCombatant} to attack a given item using
 * a projectile fired from distance.
 *
 * @author Blair Butterworth
 */
public class RangedAttack extends BasicAction
{
    private transient RangedCombatant combatant;
    private transient Projectile projectile;
    private transient Destroyable target;
    private transient Vector2 destination;
    private transient ItemFactory factory;

    private transient float flightTime;
    private transient float reloadTime;

    @Inject
    public RangedAttack(ItemFactory factory) {
        this.factory = factory;
    }

    @Override
    public boolean act(float time) {
        if (! initialized()) {
            initialize();
        }
        if (! readyToFire()) {
            reduceTimeToFire(time);
            return ActionIncomplete;
        }
        if (! projectileLaunched()) {
            fireProjectile();
            return ActionIncomplete;
        }
        if (! projectileReachedTarget()) {
            moveProjectile(time);
            return ActionIncomplete;
        }
        else {
            return hitWithProjectile();
        }
    }

    @Override
    public void reset() {
        super.reset();
        if (initialized()) {
            projectile.setVisible(false);
            combatant = null;
            projectile = null;
            target = null;
        }
    }

    @Override
    public void restart() {
        super.restart();
        if (initialized()) {
            projectile.setVisible(false);
            flightTime = 0;
            reloadTime = 0;
        }
    }

    private boolean initialized() {
        return combatant != null && projectile != null && target != null;
    }

    private void initialize() {
        flightTime = 0;
        reloadTime = 0;

        target = (Destroyable)getTarget();
        destination = target.getPosition(Alignment.Center);

        combatant = (RangedCombatant)getItem();
        reorient(combatant, target, isShip(combatant));

        projectile = getProjectile(combatant);
        projectile.setVisible(false);
        projectile.setAnimation(UnitAnimation.Idle);
        projectile.setPosition(combatant.getPosition(Alignment.Center));
    }

    private Projectile getProjectile(RangedCombatant combatant) {
        Projectile result = (Projectile)combatant.getAssociatedItem();
        if (result == null) {
            result = (Projectile)factory.get(combatant.getProjectileType());
            combatant.setAssociatedItem(result);

            ItemGroup parent = combatant.getParent();
            parent.addItem(result);
        }
        return result;
    }

    private boolean readyToFire() {
        return reloadTime == 0;
    }

    private void reduceTimeToFire(float time) {
        reloadTime = Math.max(reloadTime - time, 0);
    }

    private boolean projectileLaunched() {
        return projectile.getVisible();
    }

    private void fireProjectile() {
        flightTime = 0;
        destination = target.getPosition(Alignment.Center);

        projectile.setVisible(true);
        projectile.setPosition(combatant.getPosition(Alignment.Center));

        combatant.setAnimation(UnitAnimation.Attack);
        combatant.setSound(UnitSound.Attack);

        reorient(combatant, target, isShip(combatant));
    }

    private boolean projectileReachedTarget() {
        Vector2 position = projectile.getPosition(Alignment.Center);
        return position.equals(destination);
    }

    private void moveProjectile(float time) {
        Vector2 currentPosition = projectile.getPosition(Alignment.Center);
        Vector2 updatedPosition = getNextPosition(currentPosition, destination, time);
        projectile.setPosition(updatedPosition, Alignment.Center);
        flightTime += time;
    }

    private Vector2 getNextPosition(Vector2 position, Vector2 destination, float time) {
        Vector2 remaining = destination.cpy().sub(position);
        float remainingDistance = remaining.len();
        float incrementDistance = time * projectile.getSpeed();

        if (remainingDistance > incrementDistance) {
            Vector2 direction = remaining.nor();
            Vector2 increment = direction.scl(incrementDistance);
            return position.cpy().add(increment);
        }
        return destination;
    }

    private boolean hitWithProjectile() {
        float newHealth = getDamagedHealth(combatant, target);
        target.setHealth(newHealth);
        combatant.setSound(UnitSound.Hit);
        projectile.setVisible(false);
        reloadTime = Math.max(combatant.getAttackSpeed() - flightTime, 0);
        return newHealth == 0;
    }
}
