/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.test.data.item;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.evilbird.engine.audio.sound.Sound;
import com.evilbird.engine.common.graphics.animation.Animation;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.text.Fonts;
import com.evilbird.engine.object.AnimatedObjectStyle;
import com.evilbird.warcraft.object.layer.LayerGroupStyle;
import com.evilbird.warcraft.object.unit.UnitAnimation;
import com.evilbird.warcraft.object.unit.UnitSound;
import com.evilbird.warcraft.object.unit.UnitStyle;
import com.evilbird.warcraft.object.unit.building.BuildingStyle;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.evilbird.engine.common.graphics.renderable.EmptyRenderable.BlankRenderable;

/**
 * Provides {@link Skin Skin} test data.
 *
 * @author Blair Butterworth
 */
public class TestSkins
{
    private TestSkins() {
    }

    public static Skin newLayerSkin() {
        Skin skin = new Skin();
        skin.add("default", newLayerGroupStyle() , LayerGroupStyle.class);
        return skin;
    }

    private static LayerGroupStyle newLayerGroupStyle() {
        LayerGroupStyle style = new LayerGroupStyle();
        style.setEmpty(Mockito.mock(TiledMapTileLayer.Cell.class));
        style.setFull(Mockito.mock(TiledMapTileLayer.Cell.class));
        style.setPatterns(Collections.emptyMap());
        return style;
    }

    public static Skin newOutroMenuSkin() {
        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = Fonts.ARIAL;

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = Fonts.ARIAL;

        ProgressBar.ProgressBarStyle progressBarStyle = new ProgressBar.ProgressBarStyle();

        Skin skin = new Skin();
        skin.add("default", buttonStyle, TextButton.TextButtonStyle.class);
        skin.add("default", labelStyle, Label.LabelStyle.class);
        skin.add("font-large", labelStyle, Label.LabelStyle.class);
        skin.add("progress-outro", labelStyle, Label.LabelStyle.class);
        skin.add("progress-outro", progressBarStyle, ProgressBar.ProgressBarStyle.class);

        return skin;
    }

    public static Skin newTestSkin() {
        BuildingStyle style = newBuildingStyle();

        Skin skin = new Skin();
        skin.add("default", style, AnimatedObjectStyle.class);
        skin.add("default", style, UnitStyle.class);
        skin.add("default", style, BuildingStyle.class);
        return skin;
    }

    private static BuildingStyle newBuildingStyle() {
        BuildingStyle unitStyle = new BuildingStyle();
        unitStyle.animations = newTestAnimations();
        unitStyle.sounds = newTestSounds();
        unitStyle.lightDamage = BlankRenderable;
        unitStyle.heavyDamage = BlankRenderable;
        return unitStyle;
    }

    private static Map<Identifier, Animation> newTestAnimations() {
        Map<Identifier, Animation> result = new HashMap<>();
        for (UnitAnimation animation: UnitAnimation.values()) {
            result.put(animation, Mockito.mock(Animation.class));
        }
        return result;
    }

    private static Map<Identifier, Sound> newTestSounds() {
        Map<Identifier, Sound> result = new HashMap<>();
        for (UnitSound sound: UnitSound.values()) {
            result.put(sound, Mockito.mock(Sound.class));
        }
        return result;
    }
}
