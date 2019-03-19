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
import com.evilbird.warcraft.item.unit.resource.ResourceType;

import static com.evilbird.test.data.item.TestBuildings.newTestBuilding;
import static com.evilbird.test.data.item.TestCombatants.newTestCombatant;
import static com.evilbird.test.data.item.TestItemRoots.newTestRoot;

public class TestPlayers
{
    private TestPlayers() {
    }

    public static Player newTestPlayer(String id) {
        return newTestPlayer(new TextIdentifier(id));
    }

    public static Player newTestPlayer(Identifier identifier) {
        return newTestPlayer(identifier, newTestRoot("root"));
    }

    public static Player newTestPlayer(Identifier identifier, ItemRoot root) {
        Player player = new Player();
        player.setIdentifier(identifier);
        player.setHumanPlayer(true);
        player.setResource(ResourceType.Gold, 123);
       // player.setResource(ResourceType.Wood, 456);
        player.addItem(newTestCombatant(new TextIdentifier("footman"), UnitType.Footman, root, player));
        player.addItem(newTestBuilding(new TextIdentifier("barracks"), UnitType.Barracks, root, player));
        return player;
    }
}