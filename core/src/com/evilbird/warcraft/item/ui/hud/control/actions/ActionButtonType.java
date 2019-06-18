/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.hud.control.actions;

import com.evilbird.engine.common.lang.Identifier;

/**
 * Defines options for specifying action button types.
 *
 * @author Blair Butterworth
 */
public enum ActionButtonType implements Identifier
{
    CancelButton,
    MoveButton,
    StopButton,

    AttackButton,
    DefendButton,
    PatrolButton,

    RepairButton,
    GatherButton,

    BuildSimpleButton,
    BuildAdvancedButton,
    BuildCancelButton,

    BuildBarracksButton,
    BuildFarmButton,
    BuildLumberMillButton,
    BuildTownHallButton,
    BuildStablesButton,

    TrainFootmanButton,
    TrainPeasantButton,

    UpgradeArrowDamageButton
}
