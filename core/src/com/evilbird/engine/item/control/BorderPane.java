package com.evilbird.engine.item.control;

import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemGroup;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class BorderPane extends ItemGroup
{
    private Item center;

    @Inject
    public BorderPane()
    {
        center = null;
    }

    public Item getCenter()
    {
        return center;
    }

    public void setCenter(Item newCenter)
    {
        if (center != null){
            removeItem(center);
        }
        center = newCenter;
        addItem(center);
    }

    @Override
    public void sizeChanged()
    {
        super.sizeChanged();
        if (center != null){
            center.setSize(getSize());
        }
    }
}