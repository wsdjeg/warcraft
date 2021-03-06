/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.common.death;

import com.evilbird.test.data.item.TestBuildings;
import com.evilbird.test.data.item.TestCombatants;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.warcraft.action.death.DeathAction;
import com.evilbird.warcraft.action.death.RemoveEvents;
import com.evilbird.warcraft.action.selection.SelectEvents;
import com.evilbird.warcraft.common.WarcraftPreferences;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.unit.UnitAnimation;
import com.evilbird.warcraft.object.unit.UnitSound;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.building.Building;
import com.evilbird.warcraft.object.unit.combatant.Combatant;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static com.evilbird.warcraft.object.unit.UnitAnimation.Idle;
import static com.evilbird.warcraft.object.unit.UnitSound.Ready;

/**
 * Instances of this unit test validate the {@link DeathAction} class.
 *
 * @author Blair Butterworth
 */
public class DeathActionTest extends GameTestCase
{
    private DeathAction action;
    private SelectEvents selectEvents;
    private RemoveEvents removeEvents;

    @Before
    public void setup() {
        super.setup();
        preferences = Mockito.mock(WarcraftPreferences.class);
        selectEvents = Mockito.mock(SelectEvents.class);
        removeEvents = Mockito.mock(RemoveEvents.class);
        action = new DeathAction(selectEvents, removeEvents, objectFactory);
    }

    @Test
    public void buildingTest() {
        Building item = TestBuildings.newTestBuilding("barracks", UnitType.Barracks);
        Player parent = (Player)item.getParent();

        parent.removeObjects();
        parent.addObject(item);
        action.setSubject(item);

        item.setAnimation(Idle);
        item.setSound(Ready);
        item.setSelected(true);
        item.setSelectable(true);

        action.act(0.1f);
        Assert.assertEquals(UnitAnimation.Death, item.getAnimation());
        Assert.assertEquals(UnitSound.Die, item.getSound());
        Assert.assertFalse(item.getSelected());
        Assert.assertFalse(item.getSelectable());

        action.act(1);
        Assert.assertTrue(parent.containsObject(item));

        action.act(30);
        Assert.assertFalse(parent.containsObject(item));
    }

    @Test
    public void meleeCombatantTest() {
        Combatant item = TestCombatants.newTestCombatant("footman");
        Player parent = (Player)item.getParent();

        parent.removeObjects();
        parent.addObject(item);
        action.setSubject(item);

        item.setAnimation(Idle);
        item.setSound(Ready);
        item.setSelected(true);
        item.setSelectable(true);

        action.act(0.1f);
        Assert.assertEquals(UnitAnimation.Death, item.getAnimation());
        Assert.assertEquals(UnitSound.Die, item.getSound());
        Assert.assertFalse(item.getSelected());
        Assert.assertFalse(item.getSelectable());

        action.act(30);
        Assert.assertFalse(parent.containsObject(item));
    }

    @Test
    public void resetTest() {
        meleeCombatantTest();
        action.reset();
        meleeCombatantTest();
    }
}