/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit;

import com.evilbird.engine.common.lang.Identifier;

/**
 * Defines identifiers for unit attack varieties.
 *
 * @author Blair Butterworth
 */
public enum UnitAttack implements Identifier
{
    None,
    Melee,  //Sword + Fist
    Ranged, //Arrow + Axe
    Naval,  //Torpedo + Cannon
    Siege,  //Bolt + Rock
    Magic,
    Explosive
}
