/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.combatant.orc;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.engine.game.GameFactory;
import com.evilbird.test.testcase.FactoryTestCase;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

import java.util.Map;

/**
 * Instances of this unit test validate logic in the {@link GruntFactory} class.
 *
 * @author Blair Butterworth
 */
public class GruntFactoryTest extends FactoryTestCase<Combatant>
{
    @Override
    protected GameFactory<Combatant> newFactory(AssetManager assets) {
        return new GruntFactory(assets);
    }

    @Override
    protected Map<String, Object> newValueProperties() {
        return Maps.of("type", UnitType.Grunt,
                "HealthMaximum", 60.0f);
    }
}