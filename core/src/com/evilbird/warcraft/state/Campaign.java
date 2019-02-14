/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state;

import com.evilbird.engine.state.StateIdentifier;
import com.evilbird.warcraft.state.map.Map;
import com.evilbird.warcraft.state.hud.Hud;

public enum Campaign implements StateIdentifier, StateDefinition
{
    Human1 (Map.Human1, Hud.Human);

    private Hud hud;
    private Map map;

    Campaign(Map map, Hud hud) {
        this.hud = hud;
        this.map = map;
    }

    public Hud getHud() {
        return hud;
    }

    public Map getMap() {
        return map;
    }
}
