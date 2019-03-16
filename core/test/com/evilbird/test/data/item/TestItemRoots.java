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
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemGraph;
import com.evilbird.engine.item.ItemGroup;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.item.data.DataType;

public class TestItemRoots
{
    private TestItemRoots() {
    }

    public static ItemRoot newItemRoot(Identifier id){
        ItemGraph graph = new ItemGraph(32, 32, 128, 128);

        ItemGroup group1 = new ItemGroup();
        group1.setIdentifier(new TextIdentifier("Group1"));
        group1.setType(DataType.Player);
        group1.addItem(TestItems.newItem("Item1"));
        group1.addItem(TestItems.newItem("Item2"));

        ItemGroup group2 = new ItemGroup();
        group2.setType(DataType.Player);
        group2.setIdentifier(new TextIdentifier("Group2"));
        group2.addItem(TestItems.newItem("Item3"));
        group2.addItem(TestItems.newItem("Item4"));

        ItemRoot root = new ItemRoot();
        root.setIdentifier(id);
        root.setSpatialGraph(graph);
        root.addItem(group1);
        root.addItem(group2);

        return root;
    }

    public static ItemRoot newItemRoot(Identifier id, Item item){
        ItemRoot root = new ItemRoot();
        root.setIdentifier(id);
        root.addItem(item);
        return root;
    }
}
