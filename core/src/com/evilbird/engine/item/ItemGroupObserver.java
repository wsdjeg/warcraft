/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.item;

public interface ItemGroupObserver
{
    void itemAdded(Item item);

    void itemRemoved(Item item);

    void itemsCleared();
}