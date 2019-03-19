/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.move;

import com.evilbird.engine.action.Action;
import com.evilbird.test.testcase.ActionTestCase;

/**
 * Instances of this unit test validate the {@link MoveToLocation} class.
 *
 * @author Blair Butterworth
 */
public class MoveToLocationTest extends ActionTestCase
{
    @Override
    protected Action newAction() {
        return new MoveToLocation();
    }

    @Override
    protected Enum newActionId() {
        return MoveActions.MoveToLocation;
    }
}