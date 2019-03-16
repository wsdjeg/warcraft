/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.layer.fog;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

/**
 * Instances of this simple object represent specify the style of a fog
 * layer cell and the cells that surround it.
 *
 * @author Blair Butterworth
 */
public class FogStyle
{
    public Cell full;
    public Cell bottom;
    public Cell right;
    public Cell left;
    public Cell top;
    public Cell bottomRightInternal;
    public Cell bottomLeftInternal;
    public Cell topRightInternal;
    public Cell topLeftInternal;
    public Cell bottomRightExternal;
    public Cell bottomLeftExternal;
    public Cell topRightExternal;
    public Cell topLeftExternal;
}