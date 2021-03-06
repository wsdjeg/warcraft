/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.gatherer.orc;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.engine.game.GameContext;
import com.evilbird.test.testcase.GameFactoryTestCase;
import com.evilbird.warcraft.object.unit.combatant.gatherer.orc.TrollTankerFactory;
import com.evilbird.warcraft.state.WarcraftContext;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import static com.evilbird.warcraft.common.WarcraftFaction.Human;
import static com.evilbird.warcraft.common.WarcraftFaction.Orc;
import static com.evilbird.warcraft.common.WarcraftSeason.Summer;
import static com.evilbird.warcraft.common.WarcraftSeason.Swamp;
import static com.evilbird.warcraft.common.WarcraftSeason.Winter;
import static com.evilbird.warcraft.object.unit.UnitType.TrollTanker;

/**
 * Instances of this unit test validate logic in the {@link com.evilbird.warcraft.object.unit.combatant.gatherer.orc.TrollTankerFactory} class.
 *
 * @author Blair Butterworth
 */
public class TrollTankerFactoryTest extends GameFactoryTestCase<com.evilbird.warcraft.object.unit.combatant.gatherer.orc.TrollTankerFactory>
{
    @Override
    protected com.evilbird.warcraft.object.unit.combatant.gatherer.orc.TrollTankerFactory newFactory(DeviceDisplay display, AssetManager assets) {
        return new TrollTankerFactory(assets);
    }

    @Override
    protected Collection<GameContext> getLoadContexts() {
        return Arrays.asList(
                new WarcraftContext(Human, Summer),
                new WarcraftContext(Human, Swamp),
                new WarcraftContext(Human, Winter),
                new WarcraftContext(Orc, Summer),
                new WarcraftContext(Orc, Swamp),
                new WarcraftContext(Orc, Winter));
    }

    @Override
    protected Collection<Identifier> getProductIdentifiers() {
        return Collections.singleton(TrollTanker);
    }

    @Override
    protected Map<String, Object> getProductProperties() {
        return Maps.of("type", TrollTanker, "HealthMaximum", 90.0f);
    }
}