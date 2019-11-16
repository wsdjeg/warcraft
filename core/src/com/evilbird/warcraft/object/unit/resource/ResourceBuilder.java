/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.resource;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.graphics.renderable.FlashingRenderable;
import com.evilbird.engine.common.graphics.renderable.SpriteRenderable;
import com.evilbird.engine.object.AnimatedObjectStyle;
import com.evilbird.warcraft.object.unit.UnitAnimation;
import com.evilbird.warcraft.object.unit.UnitStyle;

/**
 * Creates a new {@link Resource} instance whose visual and audible
 * presentation is defined by the given {@link ResourceAssets}.
 *
 * @author Blair Butterworth
 */
public class ResourceBuilder
{
    private ResourceAssets assets;
    private ResourceAnimations animations;
    private ResourceSounds sounds;

    public ResourceBuilder(ResourceAssets assets) {
        this.assets = assets;
    }

    public Resource build() {
        Resource result = new Resource(getSkin());
        result.setAnimation(UnitAnimation.Idle);
        result.setSelected(false);
        result.setSelectable(true);
        result.setTouchable(Touchable.enabled);
        result.setSize(96, 96);
        return result;
    }

    private Skin getSkin() {
        UnitStyle style = getStyle();
        Skin skin = new Skin();
        skin.add("default", style, AnimatedObjectStyle.class);
        skin.add("default", style, UnitStyle.class);
        return skin;
    }

    private UnitStyle getStyle() {
        ResourceSounds sounds = getSounds();
        ResourceAnimations animations = getAnimations();

        UnitStyle style = new UnitStyle();
        style.animations = animations.get();
        style.sounds = sounds.get();
        style.selection = new SpriteRenderable(assets.getSelectionTexture());
        style.highlight = new FlashingRenderable(assets.getHighlightTexture());
        return style;
    }

    private ResourceAnimations getAnimations() {
        if (animations == null) {
            animations = new ResourceAnimations(assets);
        }
        return animations;
    }

    private ResourceSounds getSounds() {
        if (sounds == null) {
            sounds = new ResourceSounds(assets);
        }
        return sounds;
    }
}
