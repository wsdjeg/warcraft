/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.display.control.actions.buttons.orc;

import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.common.query.UnitOperations;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.ButtonController;

import java.util.List;

import static com.evilbird.warcraft.item.common.query.UnitOperations.hasResources;
import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.TrainPeonButton;
import static com.evilbird.warcraft.item.unit.UnitType.Peon;
import static java.util.Collections.singletonList;

/**
 * Controls the buttons shown when an Orc Great Hall is selected.
 *
 * @author Blair Butterworth
 */
public class GreatHallButtons implements ButtonController
{
    @Override
    public List<ActionButtonType> getButtons(Item item) {
        return singletonList(TrainPeonButton);
    }

    @Override
    public boolean getEnabled(ActionButtonType button, Item item) {
        Player player = UnitOperations.getPlayer(item);
        switch (button) {
            case TrainPeonButton: return hasResources(player, Peon);
            default: return false;
        }
    }
}