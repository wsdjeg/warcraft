package com.evilbird.warcraft.item.common.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.evilbird.engine.common.graphics.DirectionalAnimation;

import org.apache.commons.lang3.Range;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class AnimationBuilder
{
    private Texture texture;
    private AnimationSchema schema;

    public void setTexture(Texture texture)
    {
        this.texture = texture;
    }

    public void setSchema(AnimationSchema schema)
    {
        this.schema = schema;
    }

    public DirectionalAnimation build()
    {
        Map<Range<Float>, List<Rectangle>> regions = schema.getFrameRegions();
        Map<Range<Float>, Array<TextureRegion>> frames = getFrames(regions);
        PlayMode mode = schema.getLoop() ? PlayMode.LOOP : PlayMode.NORMAL;
        return new DirectionalAnimation(0f, schema.getFrameInterval(), frames, mode);
    }

    private Map<Range<Float>, Array<TextureRegion>> getFrames(Map<Range<Float>, List<Rectangle>> regions)
    {
        Map<Range<Float>, Array<TextureRegion>> result = new HashMap(regions.size());
        for (Entry<Range<Float>, List<Rectangle>> region: regions.entrySet()){
            result.put(region.getKey(), getFrames(region.getValue()));
        }
        return result;
    }

    private Array<TextureRegion> getFrames(List<Rectangle> regions)
    {
        Array<TextureRegion> result = new Array<TextureRegion>(regions.size());
        for (Rectangle region: regions){
            result.add(getFrame(region));
        }
        return result;
    }

    private TextureRegion getFrame(Rectangle region)
    {
        return new TextureRegion(
            texture,
            (int)region.getX(),
            (int)region.getY(),
            (int)region.getWidth(),
            (int)region.getHeight());
    }
}