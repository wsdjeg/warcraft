/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ui.objective;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.state.State;
import com.evilbird.test.data.item.TestItemRoots;
import com.evilbird.test.testcase.GameTestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static com.evilbird.warcraft.action.menu.MenuActions.FailureMenu;
import static com.evilbird.warcraft.action.menu.MenuActions.VictoryMenu;
import static java.util.Collections.emptyList;

/**
 * Instances of this unit test validate the {@link ObjectiveBehaviour} class.
 *
 * @author Blair Butterworth
 */
public class ObjectiveBehaviourTest extends GameTestCase
{
    private State state;
    private GameObjectContainer root;
    private Action winAction;
    private Action loseAction;
    private ActionFactory actions;
    private EventQueue events;
    private ObjectiveBehaviour behaviour;

    @Before
    public void setup() {
        winAction = Mockito.mock(Action.class);
        loseAction = Mockito.mock(Action.class);

        actions = Mockito.mock(ActionFactory.class);
        Mockito.when(actions.get(VictoryMenu)).thenReturn(winAction);
        Mockito.when(actions.get(FailureMenu)).thenReturn(loseAction);

        events = Mockito.mock(EventQueue.class);
        behaviour = new ObjectiveBehaviour(actions, events);

        state = Mockito.mock(State.class);
        root = TestItemRoots.newTestRoot("root");
        Mockito.when(state.getWorld()).thenReturn(root);
    }

    @Test
    public void winTest() {
        behaviour.setWinCondition((a, b) -> false);
        behaviour.setLoseCondition((a, b) -> false);

        behaviour.apply(state, emptyList(), 1);
        Assert.assertFalse(root.getBaseGroup().getActions().contains(winAction));

        behaviour.setWinCondition((a, b) -> true);
        behaviour.setLoseCondition((a, b) -> false);

        behaviour.apply(state, emptyList(), 1);
        Assert.assertTrue(root.getBaseGroup().getActions().contains(winAction));
    }

    @Test
    public void loseTest() {
        behaviour.setWinCondition((a, b) -> false);
        behaviour.setLoseCondition((a, b) -> false);

        behaviour.apply(state, emptyList(), 1);
        Assert.assertFalse(root.getBaseGroup().getActions().contains(loseAction));

        behaviour.setWinCondition((a, b) -> false);
        behaviour.setLoseCondition((a, b) -> true);

        behaviour.apply(state, emptyList(), 1);
        Assert.assertTrue(root.getBaseGroup().getActions().contains(loseAction));
    }
}