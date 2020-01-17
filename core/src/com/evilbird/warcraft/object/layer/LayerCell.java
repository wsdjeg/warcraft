/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.layer;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.evilbird.engine.object.BasicGameObject;
import com.evilbird.engine.object.GameObjectGroup;

import javax.inject.Inject;

/**
 * Represents a single element in a {@link LayerGroup}. Each LayerGroupCell has
 * a value, which when it reaches zero will cause it to ask its parent group to
 * assign it the style and attributes that constitute an empty cell.
 *
 * @author Blair Butterworth
 */
public class LayerCell extends BasicGameObject
{
    private static final int TILE_WIDTH = 32;
    private static final int TILE_HEIGHT = 32;

    protected float value;
    protected GridPoint2 location;

    @Inject
    public LayerCell(GridPoint2 location, float value) {
        setTouchable(Touchable.enabled);
        setVisible(true);
        setLocation(location);
        setValue(value);
    }

    public GridPoint2 getLocation() {
        return location;
    }

    public float getValue() {
        return value;
    }

    public void setLocation(GridPoint2 location) {
        this.location = location;
        setSize(TILE_WIDTH, TILE_HEIGHT);
        setPosition(location.x * TILE_WIDTH, location.y * TILE_HEIGHT);
    }

    public void setValue(float value) {
        float previous = this.value;
        this.value = Math.max(value, 0f);

        if (value == 0f && previous != 0f) {
            setEmptyTexture();
        }
        if (value != 0f && previous == 0f) {
            setFullTexture();
        }
    }

    @Override
    public void setParent(GameObjectGroup parent) {
        super.setParent(parent);
        if (value == 0f) {
            setEmptyTexture();
        } else {
            setFullTexture();
        }
    }

    protected void setEmptyTexture() {
        LayerGroup group = (LayerGroup)getParent();
        if (group != null) {
            group.setEmptyTexture(location);
        }
    }

    protected void setFullTexture() {
        LayerGroup group = (LayerGroup)getParent();
        if (group != null) {
            group.setFullTexture(location);
        }
    }
}
