/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.display.views.map;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.warcraft.object.display.components.map.MapPaneStyle;

import static com.badlogic.gdx.graphics.Color.DARK_GRAY;
import static com.evilbird.engine.common.graphics.Colours.SEMI_TRANSPARENT_70;
import static com.evilbird.engine.common.graphics.DrawableUtils.getDrawable;
import static com.evilbird.engine.common.graphics.TextureUtils.getRectangle;

/**
 * Creates a new {@link MapOverlay} instance whose visual presentation is
 * defined by the given {@link MapOverlayAssets}.
 *
 * @author Blair Butterworth
 */
public class MapOverlayBuilder 
{
    private static final Vector2 SIZE = new Vector2(384.0f, 384.0f);

    public MapOverlayBuilder() {
    }

    public MapOverlay build() {
        return new MapOverlay(getSkin());
    }

    private Skin getSkin() {
        Skin skin = new Skin();
        skin.add("default", getMapPaneStyle());
        return skin;
    }

    private MapPaneStyle getMapPaneStyle() {
        MapPaneStyle style = new MapPaneStyle();
        style.background = getDrawable(getRectangle((int)SIZE.x + 2, (int)SIZE.y + 2, DARK_GRAY));
        style.size = new Vector2(SIZE);
        style.colour = SEMI_TRANSPARENT_70;
        return style;
    }
}
