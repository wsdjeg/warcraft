/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.test.data.item;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.evilbird.engine.common.audio.SoundEffect;
import com.evilbird.engine.common.graphics.Animation;
import com.evilbird.engine.common.graphics.Fonts;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.specialized.ViewableStyle;
import com.evilbird.warcraft.item.layer.LayerGroupStyle;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.UnitSound;
import com.evilbird.warcraft.item.unit.UnitStyle;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
        style.empty =  Mockito.mock(TiledMapTileLayer.Cell.class);
        style.full =  Mockito.mock(TiledMapTileLayer.Cell.class);
        style.patterns = Collections.emptyMap();
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
        Skin skin = new Skin();
        skin.add("default", newAnimationStyle(), ViewableStyle.class);
        skin.add("default", new UnitStyle(), UnitStyle.class);
        return skin;
    }

    private static ViewableStyle newAnimationStyle() {
        ViewableStyle viewableStyle = new ViewableStyle();
        viewableStyle.animations = newTestAnimations();
        viewableStyle.sounds = newTestSounds();
        return viewableStyle;
    }

    private static Map<Identifier, Animation> newTestAnimations() {
        Map<Identifier, Animation> result = new HashMap<>();
        for (UnitAnimation animation: UnitAnimation.values()) {
            result.put(animation, Mockito.mock(Animation.class));
        }
        return result;
    }

    private static Map<Identifier, SoundEffect> newTestSounds() {
        Map<Identifier, SoundEffect> result = new HashMap<>();
        for (UnitSound sound: UnitSound.values()) {
            result.put(sound, Mockito.mock(SoundEffect.class));
        }
        return result;
    }
}