/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.display;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.game.GameContext;
import com.evilbird.engine.game.GameFactory;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectGroup;
import com.evilbird.warcraft.item.display.control.ControlPaneFactory;
import com.evilbird.warcraft.item.display.resource.ResourcePaneFactory;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

/**
 * Creates the graphical user interface shown to the user while the game plays
 * out: the heads up display (HUD).
 *
 * @author Blair Butterworth
 */
public class HudFactory implements GameFactory<GameObject>
{
    private ControlPaneFactory controlPaneFactory;
    private ResourcePaneFactory resourcePaneFactory;

    @Inject
    public HudFactory(ControlPaneFactory controlPaneFactory, ResourcePaneFactory resourcePaneFactory) {
        this.controlPaneFactory = controlPaneFactory;
        this.resourcePaneFactory = resourcePaneFactory;
    }

    @Override
    public GameObject get(Identifier identifier) {
        Validate.isInstanceOf(HudType.class, identifier);
        return get((HudType)identifier);
    }

    private GameObject get(HudType hudType) {
        GameObjectGroup hud = new GameObjectGroup();
        hud.setFillParent(true);
        hud.setTouchable(Touchable.childrenOnly);
        hud.addObject(controlPaneFactory.get(com.evilbird.warcraft.item.display.HudControl.ResourcePane));
        hud.addObject(resourcePaneFactory.get(HudControl.ControlPane));
        return hud;
    }

    @Override
    public void load(GameContext context) {
        controlPaneFactory.load(context);
        resourcePaneFactory.load(context);
    }

    @Override
    public void unload(GameContext context) {
        controlPaneFactory.unload(context);
        resourcePaneFactory.unload(context);
    }
}
