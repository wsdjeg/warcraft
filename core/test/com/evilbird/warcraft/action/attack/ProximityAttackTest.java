/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.attack;

import com.evilbird.engine.item.Item;
import com.evilbird.test.data.item.TestItems;
import com.evilbird.test.verifier.EqualityVerifier;
import com.evilbird.warcraft.common.WarcraftPreferences;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class ProximityAttackTest
{
    private Item item;
    private Item target;
    private ProximityAttack action;
    private WarcraftPreferences preferences;

    @Before
    public void setup() {
        item = TestItems.newItem("footman");
        target = TestItems.newItem("grunt");
        preferences = Mockito.mock(WarcraftPreferences.class);

        action = new ProximityAttack(preferences);
        action.setItem(item);
        action.setTarget(target);
    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(ProximityAttack.class)
                .withMockedTransientFields(Item.class)
                .excludeTransientFields()
                .verify();
    }
}