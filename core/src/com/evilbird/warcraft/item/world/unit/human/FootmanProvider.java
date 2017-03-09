package com.evilbird.warcraft.item.world.unit.human;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.evilbird.engine.common.graphics.DirectionalAnimation;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.action.ActionType;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

public class FootmanProvider implements AssetProvider<Item>
{
    private AssetManager assets;

    @Inject
    public FootmanProvider(Device device)
    {
        this.assets = device.getAssetStorage().getAssets();
    }

    @Override
    public void load()
    {
        assets.load("data/textures/human/perennial/footman.png", Texture.class);
        assets.load("data/textures/neutral/perennial/decompose.png", Texture.class);
        assets.load("data/textures/neutral/perennial/icons.png", Texture.class);
    }

    @Override
    public Item get()
    {
        com.evilbird.warcraft.item.world.unit.Unit result = new com.evilbird.warcraft.item.world.unit.Unit();
        result.setActions(getActions());
        result.setAvailableAnimations(getAnimations());
        result.setAnimation(new Identifier("Idle"));
        result.setAvailableSounds(getSounds());
        result.setArmour(1f);
        result.setDamageMinimum(2f);
        result.setDamageMaximum(9f);
        result.setHealth(100f);
        result.setHealthMaximum(100f);
        result.setIcon(getIcon());
        result.setLevel(1);
        result.setName("Footman");
        result.setRange(1f);
        result.setSelected(false);
        result.setSelectable(true);
        result.setTouchable(Touchable.enabled);
        result.setSpeed(10f);
        result.setSight(4f);
        result.setType(new Identifier("Footman"));
        result.setSize(72, 72);
        return result;
    }

    private EnumSet<ActionType> getActions()
    {
        EnumSet<ActionType> actions = EnumSet.noneOf(ActionType.class);
        actions.add(ActionType.Move);
        actions.add(ActionType.Stop);
        actions.add(ActionType.Attack);
        return actions;
    }

    private Map<Identifier, DirectionalAnimation> getAnimations()
    {
        Texture texture = assets.get("data/textures/human/perennial/footman.png", Texture.class);
        Texture decomposeTexture = assets.get("data/textures/neutral/perennial/decompose.png", Texture.class);
        return com.evilbird.warcraft.item.world.unit.common.AnimationBuilder.getAnimationSet(texture, decomposeTexture);
    }

    private Drawable getIcon()
    {
        Texture iconTexture = assets.get("data/textures/neutral/perennial/icons.png", Texture.class);
        TextureRegion iconRegion = new TextureRegion(iconTexture, 92, 0, 46, 38);
        return new TextureRegionDrawable(iconRegion);
    }

    private Map<Identifier, Sound> getSounds()
    {
        Map<Identifier, Sound> sounds = new HashMap<Identifier, Sound>();
        return sounds;
    }
}