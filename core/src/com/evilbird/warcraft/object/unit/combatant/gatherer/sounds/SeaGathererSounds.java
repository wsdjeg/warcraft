/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.combatant.gatherer.sounds;

import com.evilbird.engine.audio.sound.Sound;
import com.evilbird.engine.audio.sound.SoundCatalog;
import com.evilbird.warcraft.object.unit.combatant.gatherer.GathererAssets;

import static com.evilbird.warcraft.object.unit.UnitSound.Acknowledge;
import static com.evilbird.warcraft.object.unit.UnitSound.Build;
import static com.evilbird.warcraft.object.unit.UnitSound.Complete;
import static com.evilbird.warcraft.object.unit.UnitSound.Die;
import static com.evilbird.warcraft.object.unit.UnitSound.Ready;
import static com.evilbird.warcraft.object.unit.UnitSound.Selected;

/**
 * Defines a catalog of {@link Sound Sounds} used by sea gatherers.
 *
 * @author Blair Butterworth
 */
public class SeaGathererSounds extends SoundCatalog
{
    public SeaGathererSounds(GathererAssets assets) {
        super(6);
        sound(Selected, assets.getSelectedSound());
        sound(Acknowledge, assets.getAcknowledgeSound());
        sound(Build, assets.getConstructSound());
        sound(Complete, assets.getCompleteSound());
        sound(Ready, assets.getReadySound());
        sound(Die, assets.getDieSound());
    }
}
