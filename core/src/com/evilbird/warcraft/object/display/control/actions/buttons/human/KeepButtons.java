/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.display.control.actions.buttons.human;

import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.common.query.UnitOperations;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.display.control.actions.buttons.BasicButtonController;

import java.util.List;

import static com.evilbird.warcraft.object.common.query.UnitOperations.hasUnits;
import static com.evilbird.warcraft.object.unit.UnitType.Barracks;
import static com.evilbird.warcraft.object.unit.UnitType.Blacksmith;
import static com.evilbird.warcraft.object.unit.UnitType.Castle;
import static com.evilbird.warcraft.object.unit.UnitType.Peasant;
import static com.evilbird.warcraft.object.unit.UnitType.Stables;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

/**
 * Controls the buttons shown when a Human Keep is selected.
 *
 * @author Blair Butterworth
 */
public class KeepButtons extends BasicButtonController
{
    private static final List<com.evilbird.warcraft.object.display.control.actions.ActionButtonType> BASIC_BUTTONS =
        singletonList(com.evilbird.warcraft.object.display.control.actions.ActionButtonType.PeasantButton);

    private static final List<com.evilbird.warcraft.object.display.control.actions.ActionButtonType> ADVANCED_BUTTONS =
        asList(com.evilbird.warcraft.object.display.control.actions.ActionButtonType.PeasantButton, com.evilbird.warcraft.object.display.control.actions.ActionButtonType.CastleButton);

    @Override
    public List<com.evilbird.warcraft.object.display.control.actions.ActionButtonType> getButtons(GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);
        return player.getLevel() <= 10 ? BASIC_BUTTONS : ADVANCED_BUTTONS;
    }

    @Override
    public boolean getEnabled(com.evilbird.warcraft.object.display.control.actions.ActionButtonType button, GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);

        if (button == com.evilbird.warcraft.object.display.control.actions.ActionButtonType.PeasantButton) {
            return hasResources(player, Peasant);
        }
        if (button == com.evilbird.warcraft.object.display.control.actions.ActionButtonType.CastleButton) {
            return hasResources(player, Castle) && hasUnits(player, Barracks, Blacksmith, Stables);
        }
        return false;
    }
}
