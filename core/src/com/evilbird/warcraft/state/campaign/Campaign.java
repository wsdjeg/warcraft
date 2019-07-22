/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state.campaign;

import com.evilbird.engine.menu.MenuIdentifier;
import com.evilbird.engine.state.IntroducedState;
import com.evilbird.warcraft.menu.intro.IntroMenuType;
import com.evilbird.warcraft.state.WarcraftStateIdentifier;

/**
 * An identifier for a built-in campaign scenario.
 *
 * @author Blair Butterworth
 */
public enum Campaign implements WarcraftStateIdentifier, IntroducedState
{
    Human1,
    Human2,
    Human3,
    Human4,
    Human5,
    Human6,
    Human7,
    Human8,
    Human9,
    Human10,
    Human11,
    Human12,
    Human13,
    Human14,

    Orc1,
    Orc2,
    Orc3,
    Orc4,
    Orc5,
    Orc6,
    Orc7,
    Orc8,
    Orc9,
    Orc10,
    Orc11,
    Orc12,
    Orc13,
    Orc14;

    private static final String PATH = "data/levels/%s/campaign%d.json";
    private static final String ORC_FACTION = "orc";
    private static final String HUMAN_FACTION = "human";

    @Override
    public MenuIdentifier getIntroductionMenu() {
        return IntroMenuType.valueOf(name());
    }

    public String getFilePath() {
        return String.format(PATH,  getFaction(), getIndex());
    }

    public String getFaction() {
        return isHuman() ? HUMAN_FACTION : ORC_FACTION;
    }

    public int getIndex() {
        return ordinal() >= Orc1.ordinal() ? ordinal() - Orc1.ordinal() + 1 : ordinal() + 1;
    }

    public boolean isHuman() {
        return !isOrc();
    }

    public boolean isOrc() {
        return this.ordinal() >= Orc1.ordinal();
    }
}