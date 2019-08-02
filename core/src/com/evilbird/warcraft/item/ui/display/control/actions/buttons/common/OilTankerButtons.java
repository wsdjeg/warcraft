/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.display.control.actions.buttons.common;

import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.ButtonController;

import java.util.List;

import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.BuildOilPlatformButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.GatherButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.MoveButton;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.StopButton;
import static java.util.Arrays.asList;

/**
 * Controls the buttons shown when an oil tanker is selected.
 *
 * @author Blair Butterworth
 */
public class OilTankerButtons implements ButtonController
{
    @Override
    public List<ActionButtonType> getButtons(Item item) {
        return asList(MoveButton, StopButton, BuildOilPlatformButton, GatherButton);
    }

    @Override
    public boolean getEnabled(ActionButtonType button, Item item) {
        return button == StopButton || button == BuildOilPlatformButton;
    }
}
