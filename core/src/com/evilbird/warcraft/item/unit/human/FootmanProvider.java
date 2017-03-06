package com.evilbird.warcraft.item.unit.human;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.evilbird.engine.common.graphics.DirectionalAnimation;
import com.evilbird.engine.common.inject.AssetObjectProvider;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.action.ActionType;
import com.evilbird.warcraft.item.unit.common.AnimatedItem;
import com.evilbird.warcraft.item.unit.common.AnimationBuilder;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

public class FootmanProvider implements AssetObjectProvider<Item>
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
        /*
        Health: 60
        Armour: 2
        Damage: 2-9
        Range: 1
        Sight 4
        Speed: 10
        */

        Texture texture = assets.get("data/textures/human/perennial/footman.png", Texture.class);
        Texture decomposeTexture = assets.get("data/textures/neutral/perennial/decompose.png", Texture.class);
        Map<Identifier, DirectionalAnimation> animations = AnimationBuilder.getAnimationSet(texture, decomposeTexture);

        Texture iconTexture = assets.get("data/textures/neutral/perennial/icons.png", Texture.class);
        TextureRegion iconRegion = new TextureRegion(iconTexture, 92, 0, 46, 38);
        TextureRegionDrawable icon = new TextureRegionDrawable(iconRegion);

        EnumSet<ActionType> actions = EnumSet.noneOf(ActionType.class);
        actions.add(ActionType.Move);
        actions.add(ActionType.Stop);
        actions.add(ActionType.Attack);

        Map<Identifier, Object> properties = new HashMap<Identifier, Object>();
        properties.put(new Identifier("Animation"), new Identifier("Idle"));
        properties.put(new Identifier("Selected"), false);
        properties.put(new Identifier("Enabled"), true);
        properties.put(new Identifier("Health"), 100f);
        properties.put(new Identifier("Id"), new Identifier());
        properties.put(new Identifier("Icon"), icon);
        properties.put(new Identifier("Health"), 60.0f);
        properties.put(new Identifier("HealthMaximum"), 60.0f);
        properties.put(new Identifier("Actions"), actions);

        AnimatedItem result = new AnimatedItem(properties, animations);
        result.setType(new Identifier("Footman"));

        return result;
    }
}
