/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.common.texture;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class TextureUtils
{
    public static Drawable getDrawable(AssetManager assets, String path)
    {
        Texture iconTexture = assets.get(path, Texture.class);
        TextureRegion iconRegion = new TextureRegion(iconTexture);
        return new TextureRegionDrawable(iconRegion);
    }

    public static Drawable getDrawable(AssetManager assets, String path, int x, int y, int width, int height)
    {
        Texture iconTexture = assets.get(path, Texture.class);
        TextureRegion iconRegion = new TextureRegion(iconTexture, x, y, width, height);
        return new TextureRegionDrawable(iconRegion);
    }
}
