/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.hud.control;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar.ProgressBarStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.common.graphics.Fonts;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.device.DeviceControls;
import com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonStyle;
import com.evilbird.warcraft.item.ui.hud.control.common.HealthBarStyle;
import com.evilbird.warcraft.item.ui.hud.control.common.IconSet;
import com.evilbird.warcraft.item.ui.hud.control.common.UnitPaneStyle;
import com.evilbird.warcraft.item.ui.hud.control.status.details.building.ProductionDetailsStyle;
import com.evilbird.warcraft.item.ui.hud.control.status.selection.SelectionButtonStyle;

import javax.inject.Inject;

import static com.evilbird.engine.common.graphics.TextureUtils.getDrawable;

/**
 * Instances of this factory create {@link ControlPane ControlPanes}, loading
 * the appropriate assets for their correct display.
 *
 * @author Blair Butterworth.
 */
public class ControlPaneFactory implements AssetProvider<ControlPane>
{
    private static final String ICONS = "data/textures/common/menu/icons.png";
    private static final String ICONS_DISABLED = "data/textures/common/menu/icons_disabled.png";
    private static final String ACTION_BUTTON = "data/textures/common/menu/action.png";
    private static final String UNSELECT_BUTTON = "data/textures/common/menu/unselect.png";
    private static final String ACTION_PANEL = "data/textures/human/menu/action_panel.png";
    private static final String DETAILS_PANEL = "data/textures/human/menu/details_panel.png";
    private static final String MENU_PANEL = "data/textures/human/menu/menu_panel.png";
    private static final String MINIMAP_PANEL = "data/textures/human/menu/minimap_panel.png";
    private static final String SELECTION_PANEL = "data/textures/human/menu/selection_panel.png";
    private static final String UNIT_PANEL = "data/textures/common/menu/selection.png";
    private static final String BUTTON_ENABLED = "data/textures/human/menu/button-thin-medium-normal.png";
    private static final String BUTTON_SELECTED = "data/textures/human/menu/button-thin-medium-pressed.png";
    private static final String BUTTON_DISABLED = "data/textures/human/menu/button-thin-medium-grayed.png";
    private static final String BUILDING_FILL = "data/textures/common/menu/building_progress_bar.png";
    private static final String BUILDING_BACKGROUND="data/textures/common/menu/building_progress_background.png";
    private static final String HEALTH_PROGRESS_HIGH = "data/textures/common/menu/health_bar_high.png";
    private static final String HEALTH_PROGRESS_MEDIUM = "data/textures/common/menu/health_bar_medium.png";
    private static final String HEALTH_PROGRESS_LOW = "data/textures/common/menu/health_bar_low.png";

    private AssetManager assets;
    private DeviceControls controls;

    @Inject
    public ControlPaneFactory(Device device) {
        this(device.getAssetStorage(), device.getDeviceControls());
    }

    public ControlPaneFactory(AssetManager assets, DeviceControls controls) {
        this.assets = assets;
        this.controls = controls;
    }

    @Override
    public void load() {
        loadActionAssets();
        loadPanelAssets();
        loadButtonAssets();
        loadBuildingAssets();
        loadHealthAssets();
    }

    private void loadActionAssets() {
        assets.load(ACTION_BUTTON, Texture.class);
        assets.load(ICONS, Texture.class);
        assets.load(ICONS_DISABLED, Texture.class);
        assets.load(ACTION_PANEL, Texture.class);
    }

    private void loadPanelAssets() {
        assets.load(DETAILS_PANEL, Texture.class);
        assets.load(MENU_PANEL, Texture.class);
        assets.load(MINIMAP_PANEL, Texture.class);
        assets.load(SELECTION_PANEL, Texture.class);
        assets.load(UNIT_PANEL, Texture.class);
    }

    private void loadBuildingAssets() {
        assets.load(BUILDING_FILL, Texture.class);
        assets.load(BUILDING_BACKGROUND, Texture.class);
    }

    private void loadHealthAssets() {
        assets.load(HEALTH_PROGRESS_HIGH, Texture.class);
        assets.load(HEALTH_PROGRESS_MEDIUM, Texture.class);
        assets.load(HEALTH_PROGRESS_LOW, Texture.class);
    }

    private void loadButtonAssets() {
        assets.load(UNSELECT_BUTTON, Texture.class);
        assets.load(BUTTON_ENABLED, Texture.class);
        assets.load(BUTTON_SELECTED, Texture.class);
        assets.load(BUTTON_DISABLED, Texture.class);
    }

    @Override
    public ControlPane get() {
        return new ControlPane(getSkin());
    }

    private Skin getSkin() {
        Skin skin = new Skin();
        skin.add("default", getFontStyle());
        skin.add("default", getHealthBarStyle());
        skin.add("default", getSelectionButtonStyle());
        skin.add("default", getProductionDetailsStyle());
        skin.add("default", getControlPaneStyle());
        skin.add("default", getUnitPaneStyle());
        skin.add("button-thin-medium", getButtonStyle());
        skin.add("building-progress", getBuildingProgressStyle());
        skin.add("action-button", getActionButtonStyle());
        skin.add("action-panel", getDrawable(assets, ACTION_PANEL), Drawable.class);
        skin.add("details-panel", getDrawable(assets, DETAILS_PANEL), Drawable.class);
        skin.add("menu-panel", getDrawable(assets, MENU_PANEL), Drawable.class);
        skin.add("minimap-panel", getDrawable(assets, MINIMAP_PANEL), Drawable.class);
        skin.add("selection-panel", getDrawable(assets, SELECTION_PANEL), Drawable.class);
        return skin;
    }

    private ControlPaneStyle getControlPaneStyle() {
        ControlPaneStyle style = new ControlPaneStyle();
        style.showActions = true;
        style.showStatus = true;
        style.showMenuButton = !controls.hasMenuButton();
        style.showMiniMap = style.showMenuButton;
        return style;
    }

    private LabelStyle getFontStyle() {
        LabelStyle style = new LabelStyle();
        style.font = Fonts.ARIAL;
        style.fontColor = Color.WHITE;
        return style;
    }

    private TextButtonStyle getButtonStyle() {
        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = Fonts.ARIAL;
        textButtonStyle.fontColor = Color.WHITE;
        textButtonStyle.up = getDrawable(assets, BUTTON_ENABLED);
        textButtonStyle.over = textButtonStyle.up;
        textButtonStyle.checked = textButtonStyle.up;
        textButtonStyle.checkedOver = textButtonStyle.up;
        textButtonStyle.disabled = getDrawable(assets, BUTTON_DISABLED);
        textButtonStyle.down = getDrawable(assets, BUTTON_SELECTED);
        return textButtonStyle;
    }

    private ProgressBarStyle getBuildingProgressStyle() {
        ProgressBarStyle style = new ProgressBarStyle();
        style.background = getDrawable(assets, BUILDING_BACKGROUND);
        style.knob = getDrawable(assets, BUILDING_FILL);
        style.knobBefore = style.knob;
        return style;
    }

    private ProductionDetailsStyle getProductionDetailsStyle() {
        ProductionDetailsStyle style = new ProductionDetailsStyle();
        style.trainBackground = getDrawable(assets, ACTION_BUTTON);
        style.trainPeasantIcon = getDrawable(assets, ICONS, 0, 0, 46, 38);
        style.trainFootmanIcon = getDrawable(assets, ICONS, 92, 0, 46, 38);
        style.upgradeArrowDamageIcon = getDrawable(assets, ICONS, 184, 912, 46, 38);
        return style;
    }

    private HealthBarStyle getHealthBarStyle() {
        HealthBarStyle style = new HealthBarStyle();
        style.highBar = getDrawable(assets, HEALTH_PROGRESS_HIGH);
        style.mediumBar = getDrawable(assets, HEALTH_PROGRESS_MEDIUM);
        style.lowBar = getDrawable(assets, HEALTH_PROGRESS_LOW);
        return style;
    }

    private SelectionButtonStyle getSelectionButtonStyle() {
        SelectionButtonStyle style =  new SelectionButtonStyle();
        style.closeButtonEnabled = getDrawable(assets, UNSELECT_BUTTON);
        style.closeButtonDisabled = getDrawable(assets, UNSELECT_BUTTON);
        style.closeButtonSelected = getDrawable(assets, UNSELECT_BUTTON);
        return style;
    }

    private ActionButtonStyle getActionButtonStyle() {
        Texture icons = assets.get(ICONS, Texture.class);
        Texture disabled = assets.get(ICONS_DISABLED, Texture.class);
        return getActionButtonStyle(icons, disabled);
    }

    private ActionButtonStyle getActionButtonStyle(Texture icons, Texture disabled) {
        ActionButtonStyle style = new ActionButtonStyle();
        style.icons = new IconSet(icons);
        style.disabledIcons = new IconSet(disabled);
        style.background = getDrawable(assets, ACTION_BUTTON);
        return style;
    }

    private UnitPaneStyle getUnitPaneStyle() {
        UnitPaneStyle style = new UnitPaneStyle();
        style.icons = new IconSet(assets.get(ICONS, Texture.class));
        style.background = getDrawable(assets, UNIT_PANEL);
        return style;
    }
}
