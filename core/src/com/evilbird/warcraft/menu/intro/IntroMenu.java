/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.intro;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.evilbird.engine.common.control.SelectListener;
import com.evilbird.engine.common.control.SelectListenerAdapter;
import com.evilbird.engine.menu.Menu;

import javax.inject.Inject;

import static com.badlogic.gdx.scenes.scene2d.ui.Value.percentHeight;
import static com.badlogic.gdx.scenes.scene2d.ui.Value.percentWidth;

public class IntroMenu extends Menu
{
    private Skin skin;
    private Table table;
    private TextButton button;
    private Label title;
    private Label description;
    private Label objectives;

    @Inject
    public IntroMenu(Skin skin) {
        this.skin = skin;
        this.table = createTable(skin);
        this.title = createTitle(skin, table);
        this.description = createDescription(skin, table);
        this.objectives = createObjectives(skin, table);
        this.button = createButton(skin, table);
    }

    public Skin getSkin() {
        return skin;
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public void setDescription(String description) {
        this.description.setText(description);
    }

    public void setObjectives(String objectives) {
        this.objectives.setText(objectives);
    }

    public void setButtonAction(SelectListener action) {
        button.addListener(new SelectListenerAdapter(action));
    }

    private Table createTable(Skin skin) {
        Table table = new Table(skin);
        table.setFillParent(true);
        table.setBackground("intro-background");

        Stage stage = getStage();
        stage.addActor(table);

        return table;
    }

    private Label createTitle(Skin skin, Table table) {
        Label result = new Label("Title", skin);
        result.setAlignment(Align.center);

        Cell cell = table.add(result);
        cell.fillX();
        cell.expandX();
        cell.center();
        table.row();

        return result;
    }

    private Label createDescription(Skin skin, Table table) {
        Label result = new Label("Description", skin);
        result.setWrap(true);

        Cell cell = table.add(result);
        cell.align(Align.left);
        cell.width(percentWidth(0.65f, table));
        cell.height(percentHeight(0.35f, table));
        cell.pad(30);
        table.row();

        return result;
    }

    private Label createObjectives(Skin skin, Table table) {
        Label result = new Label("Objectives", skin);

        Cell cell = table.add(result);
        cell.align(Align.right);
        cell.width(percentWidth(0.4f, table));
        cell.height(percentHeight(0.4f, table));
        table.row();

        return result;
    }

    private TextButton createButton(Skin skin, Table table) {
        TextButton result = new TextButton("Continue", skin);

        Cell cell = table.add(result);
        cell.align(Align.right);
        cell.padRight(20);
        table.row();

        return result;
    }
}
