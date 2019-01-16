/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.sequence;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.ActionContext;
import com.evilbird.engine.action.ActionIdentifier;
import com.evilbird.engine.action.common.AnimateAction;
import com.evilbird.engine.action.common.ReplacementAction;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.animated.Animated;
import com.evilbird.warcraft.action.ActionProvider;
import com.evilbird.warcraft.item.unit.UnitAnimation;

import javax.inject.Inject;

/**
 * Instances of this class stop a given {@link Item} from gathering.
 *
 * @author Blair Butterworth
 */
//TODO: Have gatherer keep some resources
public class GatherCancel implements ActionProvider
{
    @Inject
    public GatherCancel(){
    }

    @Override
    public Action get(ActionIdentifier action, ActionContext context) {
        Item item = context.getItem();
        Action idleAnimation = new AnimateAction((Animated)item, UnitAnimation.Idle);
        return new ReplacementAction(item, idleAnimation);
    }
}
