/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.ingame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.List.ListStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.common.graphics.Fonts;
import com.evilbird.engine.common.inject.IdentifiedAssetProvider;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.menu.Menu;
import com.evilbird.engine.menu.MenuIdentifier;
import com.evilbird.engine.state.StateService;
import com.evilbird.warcraft.menu.common.controls.StyledField;
import com.evilbird.warcraft.menu.common.controls.StyledList;
import com.evilbird.warcraft.menu.common.events.SelectListener;
import com.evilbird.warcraft.state.user.UserState;
import org.apache.commons.lang3.tuple.Pair;

import javax.inject.Inject;
import java.util.Collection;

import static com.evilbird.engine.common.graphics.TextureUtils.getDrawable;
import static com.evilbird.engine.common.graphics.TextureUtils.getTiledDrawable;
import static com.evilbird.warcraft.menu.ingame.IngameMenuLayout.*;
import static com.evilbird.warcraft.menu.ingame.IngameMenuType.*;
import static com.evilbird.warcraft.menu.intro.IntroMenuType.HumanLevel1;
import static com.evilbird.warcraft.menu.main.MainMenuType.Home;
import static com.evilbird.warcraft.state.StateType.UserState;

/**
 * Instances of this factory create {@link IngameMenu} instances, menus shown
 *
 */
//TODO - Get objectives from state
//TODO - Get restart point from state
//TODO - button click sound
@SuppressWarnings("unchecked")
public class IngameMenuFactory implements IdentifiedAssetProvider<Menu>
{
    private static final String BUTTON_ENABLED = "data/textures/human/menu/button-large-normal.png";
    private static final String BUTTON_DISABLED = "data/textures/human/menu/button-large-grayed.png";
    private static final String BUTTON_SELECTED = "data/textures/human/menu/button-large-pressed.png";
    private static final String TEXT_PANEL_NORMAL = "data/textures/human/menu/text_panel_normal.png";
    private static final String BACKGROUND_NORMAL = "data/textures/human/menu/panel_normal.png";
    private static final String BACKGROUND_WIDE = "data/textures/human/menu/panel_wide.png";
    private static final String BACKGROUND_SMALL = "data/textures/human/menu/panel_small.png";
    private static final String CLICK = "data/sounds/menu/click.mp3";

    private AssetManager assets;
    private StateService states;

    @Inject
    public IngameMenuFactory(Device device, StateService states) {
        this.states = states;
        this.assets = device.getAssetStorage();
    }

    @Override
    public void load() {
        assets.load(BACKGROUND_NORMAL, Texture.class);
        assets.load(BACKGROUND_WIDE, Texture.class);
        assets.load(BACKGROUND_SMALL, Texture.class);
        assets.load(BUTTON_ENABLED, Texture.class);
        assets.load(BUTTON_DISABLED, Texture.class);
        assets.load(BUTTON_SELECTED, Texture.class);
        assets.load(TEXT_PANEL_NORMAL, Texture.class);
        assets.load(CLICK, Sound.class);
    }

    @Override
    public Menu get(Identifier identifier) {
        IngameMenu menu = new IngameMenu(getSkin());
        setLayout(menu, identifier);
        return menu;
    }

    private Skin getSkin() {
        Skin skin = new Skin();
        addButtonStyle(skin);
        addLabelStyle(skin);
        addListStyle(skin);
        addTextFieldStyle(skin);
        addMenuStyle(skin);
        return skin;
    }

    private void addButtonStyle(Skin skin) {
        Drawable enabled = getDrawable(assets, BUTTON_ENABLED);
        Drawable disabled = getDrawable(assets, BUTTON_DISABLED);
        Drawable selected = getDrawable(assets, BUTTON_SELECTED);

        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = Fonts.ARIAL;
        textButtonStyle.fontColor = Color.WHITE;
        textButtonStyle.up = enabled;
        textButtonStyle.over = enabled;
        textButtonStyle.checked = enabled;
        textButtonStyle.checkedOver = enabled;
        textButtonStyle.disabled = disabled;
        textButtonStyle.down = selected;

        //menu.setButtonSound(assets.get(CLICK));

        skin.add("default", textButtonStyle);
    }

    private void addLabelStyle(Skin skin) {
        LabelStyle labelStyle = new LabelStyle();
        labelStyle.font = Fonts.ARIAL;
        labelStyle.fontColor = Color.GOLD;

        skin.add("default", labelStyle);
    }

    private void addListStyle(Skin skin) {
        ListStyle listStyle = new ListStyle();
        listStyle.font = Fonts.ARIAL;
        listStyle.fontColorSelected = Color.GOLD;
        listStyle.fontColorUnselected = Color.WHITE;
        listStyle.background = getTiledDrawable(assets, TEXT_PANEL_NORMAL);
        listStyle.selection = new BaseDrawable();

        skin.add("default", listStyle);
    }

    private void addTextFieldStyle(Skin skin) {
        TextFieldStyle textFieldStyle = new TextFieldStyle();
        textFieldStyle.font = Fonts.ARIAL;
        textFieldStyle.fontColor = Color.GOLD;
        textFieldStyle.background = getDrawable(assets, TEXT_PANEL_NORMAL);
        skin.add("default", textFieldStyle);
    }

    private void addMenuStyle(Skin skin) {
        skin.add("menu-background-normal", getDrawable(assets, BACKGROUND_NORMAL), Drawable.class);
        skin.add("menu-background-wide", getDrawable(assets, BACKGROUND_WIDE), Drawable.class);
        skin.add("menu-background-small", getDrawable(assets, BACKGROUND_SMALL), Drawable.class);
    }

    private void setLayout(IngameMenu menu, Identifier identifier) {
        switch ((IngameMenuType)identifier) {
            case Root: { setRootLayout(menu); break; }
            case Save: { setSaveLayout(menu); break; }
            case Load: { setLoadLayout(menu); break; }
            case Exit: { setExitLayout(menu); break; }
            case Confirm: { setConfirmLayout(menu); break; }
            case Failure: { setFailureLayout(menu); break; }
            case Options: { setOptionsLayout(menu); break; }
            case Sounds: { setSoundsLayout(menu); break; }
            case Speeds: { setSpeedsLayout(menu); break; }
            case Preferences: { setPreferencesLayout(menu); break; }
            case Objectives: { setObjectivesLayout(menu); break; }
            default: throw new UnsupportedOperationException();
        }
    }

    private void setRootLayout(IngameMenu menu) {
        menu.setLayout(Normal);
        menu.addTitle("Game Menu");
        menu.addButton("Save", showMenu(menu, Save));
        menu.addButton("Load", showMenu(menu, Load));
        menu.addButton("Options", showMenu(menu, Options));
        menu.addButton("Scenario Objectives", showMenu(menu, Objectives));
        menu.addButton("End Scenario", showMenu(menu, Exit));
        menu.addSpacer();
        menu.addButton("Return to Game", showState(menu));
    }

    private void setSaveLayout(IngameMenu menu) {
        menu.setLayout(Wide);
        menu.addTitle("Save Game");

        StyledField field = menu.addTextField("");
        StyledList list = menu.addList();
        menu.addButtonRow(
            Pair.of("Save", saveState(menu, field)),
            Pair.of("Delete", deleteState(menu, list)),
            Pair.of("Cancel", showState(menu)));

        addStates(menu, list);
    }

    private void setLoadLayout(IngameMenu menu) {
        menu.setLayout(Wide);
        menu.addTitle("Load Game");

        StyledList list = menu.addList();
        menu.addButtonRow(
            Pair.of("Load", loadState(menu, list)),
            Pair.of("Delete", deleteState(menu, list)),
            Pair.of("Cancel", showState(menu)));

        addStates(menu, list);
    }

    private void setExitLayout(IngameMenu menu) {
        menu.setLayout(Normal);
        menu.addTitle("End Scenario");
        menu.addButton("Restart Scenario", showMenu(menu, HumanLevel1));
        menu.addButton("Surrender", showMenu(menu, Confirm));
        menu.addButton("Quit to Menu", showMenu(menu, Confirm));
        menu.addButton("Exit Program", shutdown(menu));
        menu.addSpacer();
        menu.addButton("Previous", showMenu(menu, Root));
    }

    private void setConfirmLayout(IngameMenu menu) {
        menu.setLayout(Normal);
        menu.addTitle("Are you sure you want to surrender to your enemies?");
        menu.addButton("Surrender", showMenu(menu, Failure));
        menu.addSpacer();
        menu.addButton("Cancel", showState(menu));
    }

    private void setFailureLayout(IngameMenu menu) {
        menu.setLayout(Small);
        menu.addTitle("You failed to achieve victory!");
        menu.addSpacer();
        menu.addButton("OK", showMenu(menu, Home));
    }

    private void setOptionsLayout(IngameMenu menu) {
        menu.setLayout(Normal);
        menu.addTitle("Game Options");
        menu.addButton("Sounds", showMenu(menu, Sounds));
        menu.addButton("Speeds", showMenu(menu, Speeds));
        menu.addButton("Preferences", showMenu(menu, Preferences));
        menu.addSpacer();
        menu.addButton("Previous", showMenu(menu, Root));
    }

    private void setSoundsLayout(IngameMenu menu) {
        menu.setLayout(Normal);
        menu.addTitle("Sound Settings");
        menu.addSpacer();
        menu.addButtonRow(
            Pair.of("OK", showState(menu)),
            Pair.of("Cancel", showState(menu)));
    }

    private void setSpeedsLayout(IngameMenu menu) {
        menu.setLayout(Normal);
        menu.addTitle("Speed Settings");
        menu.addSpacer();
        menu.addButtonRow(
            Pair.of("OK", showState(menu)),
            Pair.of("Cancel", showState(menu)));
    }

    private void setPreferencesLayout(IngameMenu menu) {
        menu.setLayout(Normal);
        menu.addTitle("Preferences");
        menu.addSpacer();
        menu.addButtonRow(
            Pair.of("OK", showState(menu)),
            Pair.of("Cancel", showState(menu)));
    }

    private void setObjectivesLayout(IngameMenu menu) {
        menu.setLayout(Normal);
        menu.addTitle("Scenario Objectives");
        menu.addLabel(" - Build four Farms");
        menu.addLabel(" - Build a Barracks");
        menu.addSpacer();
        menu.addButton("Previous", showMenu(menu, Root));
    }

    private void addStates(IngameMenu menu, List list) {
        try {
            Collection<Identifier> items = states.list(UserState);
            list.setItems(items.toArray());
        }
        catch (Exception exception) {
            exception.printStackTrace();
            //TODO - log error
            //TODO - show error menu
        }
    }

    private SelectListener saveState(IngameMenu menu, StyledField field) {
        return () -> {
            try {
                menu.saveState(new UserState(field.getText()));
            }
            catch (Exception exception) {
                exception.printStackTrace();
                //TODO - log error
                //TODO - show error menu
            }
        };
    }

    private SelectListener deleteState(IngameMenu menu, StyledList list) {
        return () -> {
            try {
                states.remove((UserState)list.getSelected());
            }
            catch (Exception exception) {
                exception.printStackTrace();
                //TODO - log error
                //TODO - show error menu
            }
        };
    }

    private SelectListener loadState(IngameMenu menu, StyledList list) {
        return () -> {
            try {
                menu.showState((UserState)list.getSelected());
            }
            catch (Exception exception) {
                exception.printStackTrace();
                //TODO - log error
                //TODO - show error menu
            }
        };
    }

    private SelectListener showState(IngameMenu menu) {
        return menu::showState;
    }

    private SelectListener showMenu(IngameMenu menu, IngameMenuType type) {
        return () -> menu.showMenuOverlay(type);
    }

    private SelectListener showMenu(IngameMenu menu, MenuIdentifier type) {
        return () -> menu.showMenu(type);
    }

    private SelectListener shutdown(IngameMenu menu) {
        return () -> Gdx.app.exit();
    }
}