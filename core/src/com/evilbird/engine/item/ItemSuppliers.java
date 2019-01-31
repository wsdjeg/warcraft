/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item;

import com.evilbird.engine.common.function.Supplier;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.warcraft.item.common.capability.Destroyable;
import com.evilbird.warcraft.item.common.capability.ResourceContainer;
import com.evilbird.warcraft.item.unit.resource.ResourceType;

/**
 * Instances of this class provide commonly used {@link Supplier Suppliers}
 * that operate on {@link Item Items}.
 *
 * @author Blair Butterworth
 */
public class ItemSuppliers
{
    public static Supplier<Boolean> isAlive(final Destroyable item) {
        return new Supplier<Boolean>() {
            @Override
            public Boolean get() {
                return (item.getHealth() > 0);
            }
        };
    }

    public static Supplier<Boolean> hasResources(final ResourceContainer container, final ResourceType type) {
        return new Supplier<Boolean>() {
            public Boolean get() {
                return (container.getResource(type) > 0);
            }
        };
    }

    public static Supplier<Item> findClosest(final Item item) {
        return new Supplier<Item>() {
            public Item get() {
                return ItemOperations.findClosest(item);
            }
        };
    }

    public static Supplier<Item> findClosest(final ItemComposite group, final Identifier type, final Item locus) {
        return new Supplier<Item>() {
            public Item get() {
                return ItemOperations.findClosest(group, type, locus);
            }
        };
    }
}
