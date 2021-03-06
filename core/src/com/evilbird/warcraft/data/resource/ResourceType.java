/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.data.resource;

import com.evilbird.engine.common.lang.Identifier;

/**
 * Implementors of this interface specify unique identifiers for resources.
 *
 * @author Blair Butterworth
 */
public enum ResourceType implements Identifier
{
    Gold,
    Oil,
    Wood,
    Food
}
