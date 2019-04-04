/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.test.data.item;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.TextIdentifier;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.resource.Resource;
import com.evilbird.warcraft.item.unit.resource.ResourceType;

import static com.evilbird.test.data.item.TestPlayers.newTestPlayer;

public class TestResources
{
    private TestResources() {
    }

    public static Resource newTestResource(String id) {
        return newTestResource(new TextIdentifier(id), UnitType.GoldMine);
    }

    public static Resource newTestResource(Identifier identifier, Identifier type) {
        return newTestResource(identifier, type, TestItemRoots.newTestRoot("root"), newTestPlayer("parent"));
    }

    public static Resource newTestResource(Identifier identifier, Identifier type, ItemRoot root, Player parent) {
        Resource resource = new Resource();
        resource.setIdentifier(identifier);
        resource.setType(type);
        resource.setPosition(12, 34);
        resource.setSize(56, 78);
        resource.setParent(parent);
        resource.setRoot(root);
        resource.setResource(ResourceType.Gold, 123);
        resource.setResource(ResourceType.Wood, 456);
        return resource;
    }
}