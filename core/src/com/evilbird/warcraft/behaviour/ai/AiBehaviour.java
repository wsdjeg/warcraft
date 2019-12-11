/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.ai;

import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.state.State;
import com.evilbird.warcraft.behaviour.ai.attack.AttackBehaviour;
import com.evilbird.warcraft.behaviour.ai.critter.WanderBehaviour;
import com.evilbird.warcraft.behaviour.ai.idle.IdleBehaviour;
import com.evilbird.warcraft.behaviour.ai.submarine.SubmarineBehaviour;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Instances of this class modify the game state on behalf of the non-human
 * players.
 *
 * @author Blair Butterworth
 */
public class AiBehaviour implements Behaviour
{
    private Collection<AiBehaviourElement> components;

    @Inject
    public AiBehaviour(
        AttackBehaviour attackBehaviour,
        IdleBehaviour idleBehaviour,
        SubmarineBehaviour submarineBehaviour,
        WanderBehaviour wanderBehaviour)
    {
        components = new ArrayList<>();
        components.add(attackBehaviour);
        components.add(idleBehaviour);
        components.add(submarineBehaviour);
        components.add(wanderBehaviour);
    }

    @Override
    public void update(State state, List<UserInput> input, float time) {
        GameObjectContainer world = state.getWorld();
        for (AiBehaviourElement component: components) {
            component.applyBehaviour(world, time);
        }
    }
}
