/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.control;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Align;
import com.evilbird.engine.item.Item;

public class TextLabel extends Item
{
    private Label label;

    @Override
    protected Actor newDelegate()
    {
        label = new Label("", getDefaultStyle());
        label.setAlignment(Align.left);
        return label;
    }

    public void setAlignment(TextLabelAlignment alignment)
    {
        label.setAlignment(alignment.getGdxEquivalent());
    }

    public void setColor(Color color)
    {
        label.setColor(color);
    }

    public void setText(String text)
    {
        label.setText(text);
        label.layout();
    }

    private LabelStyle getDefaultStyle()
    {
        Color labelColor = Color.WHITE;
        BitmapFont labelFont = new BitmapFont();
        return new LabelStyle(labelFont, labelColor);
    }
}