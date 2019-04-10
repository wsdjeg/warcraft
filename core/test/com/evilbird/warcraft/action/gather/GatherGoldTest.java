/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.gather;

import com.evilbird.engine.action.Action;
import com.evilbird.test.testcase.ActionTestCase;
import org.mockito.Mockito;

/**
 * Instances of this unit test validate the {@link GatherWood} class.
 *
 * @author Blair Butterworth
 */
public class GatherGoldTest extends ActionTestCase
{
    @Override
    protected Action newAction() {
        GatherGold action = new GatherGold(Mockito.mock(GatherReporter.class));
        action.setIdentifier(GatherActions.GatherGold);
        return action;
    }

    @Override
    protected Enum newIdentifier() {
        return GatherActions.GatherGold;
    }
}