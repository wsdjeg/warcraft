/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.display.components.menu;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.evilbird.engine.object.control.Table;

import static com.evilbird.warcraft.object.display.components.UserInterfaceComponent.MenuPane;

/**
 * Represents a user interface panel displayed at the top of the heads
 * up display control bar. Contains a button that when clicked displays the
 * in-game menu.
 *
 * @author Blair Butterworth
 */
public class MenuPane extends Table
{
    public MenuPane(Skin skin) {
        initialize(skin);
        addControls(skin);
    }

    private void initialize(Skin skin) {
        setSkin(skin);
        setSize(176, 24);
        setCentered();
        setBackground("menu-panel");
        setTouchable(Touchable.enabled);
        setType(MenuPane);
        setIdentifier(MenuPane);
    }

    private void addControls(Skin skin) {
        TextButton button = new TextButton("Menu", skin, "button-thin-medium");
        add(button);
    }
}
