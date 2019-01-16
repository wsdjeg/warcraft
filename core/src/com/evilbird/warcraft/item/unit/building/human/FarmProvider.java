package com.evilbird.warcraft.item.unit.building.human;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.evilbird.engine.common.graphics.DirectionalAnimation;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.animated.AnimationIdentifier;
import com.evilbird.warcraft.item.common.animation.AnimationCollections;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.building.Building;

import javax.inject.Inject;
import java.util.Map;

public class FarmProvider implements AssetProvider<Item>
{
    private AssetManager assets;

    @Inject
    public FarmProvider(Device device) {
        this.assets = device.getAssetStorage().getAssets();
    }

    @Override
    public void load() {
        assets.load("data/textures/human/winter/farm.png", Texture.class);
        assets.load("data/textures/neutral/perennial/construction.png", Texture.class);
        assets.load("data/textures/neutral/winter/destroyed_site.png", Texture.class);
        assets.load("data/textures/neutral/perennial/icons.png", Texture.class);
    }

    @Override
    public Item get() {
        Building result = new Building();
        result.setAvailableAnimations(getAnimations());
        result.setAnimation(UnitAnimation.Idle);
        result.setHealth(400.0f);
        result.setHealthMaximum(400.0f);
        result.setIcon(getIcon());
        result.setName("AddFarmPlaceholder");
        result.setType(UnitType.Farm);
        result.setSize(64, 64);
        return result;
    }

    private Map<AnimationIdentifier, DirectionalAnimation> getAnimations() {
        Texture general = assets.get("data/textures/human/winter/farm.png", Texture.class);
        Texture construction = assets.get("data/textures/neutral/perennial/construction.png", Texture.class);
        Texture destruction = assets.get("data/textures/neutral/winter/destroyed_site.png", Texture.class);
        return AnimationCollections.buildingAnimations(general, construction, destruction, 64, 64);
    }

    private Drawable getIcon() {
        Texture iconTexture = assets.get("data/textures/neutral/perennial/icons.png", Texture.class);
        TextureRegion iconRegion = new TextureRegion(iconTexture, 138, 266, 46, 38);
        return new TextureRegionDrawable(iconRegion);
    }
}
