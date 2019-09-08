/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.layer.fog;

import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.warcraft.item.layer.LayerGroupAdapter;
import com.evilbird.warcraft.item.layer.LayerGroupCell;

/**
 * Instances of this class serialize and deserialize {@link Fog} objects.
 *
 * @author Blair Butterworth
 */
public class FogAdapter extends LayerGroupAdapter<Fog>
{
    private static final String FOG = "fog";
    private static final String CLOUD = "cloud";

    @Override
    protected String getCellArrayProperty() {
        return FOG;
    }

    @Override
    protected String getValueProperty() {
        return CLOUD;
    }

    @Override
    protected LayerGroupCell createCell(GridPoint2 location, float value) {
        return new FogCell(location, value);
    }
}