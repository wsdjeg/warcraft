/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.construct;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemType;
import com.evilbird.warcraft.action.common.resource.ResourceTransferObserver;

public interface ConstructObserver extends ResourceTransferObserver
{
    void onConstruct(Item builder, ItemType type, Vector2 location);
}