package com.evilbird.warcraft.behaviour.user.interaction;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.Objects;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.device.UserInputType;
import com.evilbird.engine.item.Item;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public abstract class AbstractInteraction implements Interaction
{
    protected Identifier targetType;
    protected Identifier hudType;
    protected Identifier selectedType;
    protected UserInputType inputType;

    public AbstractInteraction()
    {
        this.inputType = null;
        this.targetType = null;
        this.selectedType = null;
        this.hudType = null;
    }

    public void setInputType(UserInputType inputType)
    {
        this.inputType = inputType;
    }

    public void setTargetType(String targetType)
    {
        this.targetType = targetType != null ? new Identifier(targetType) : null; //TODO;
    }

    public void setSelectedType(String selectedType)
    {
        this.selectedType = selectedType != null ? new Identifier(selectedType) : null; //TODO;
    }

    public void setHudType(String hudType)
    {
        this.hudType = hudType != null ? new Identifier(hudType) : null; //TODO;
    }

    @Override
    public boolean update(UserInput input, Item target, Item worldSelection, Item hudSelection)
    {
        if (applies(input, target, worldSelection, hudSelection))
        {
            apply(input, target, worldSelection);
            return true;
        }
        return false;
    }

    protected boolean applies(UserInput input, Item target, Item worldSelection, Item hudSelection)
    {
        if (inputType != null && !Objects.equals(inputType, input.getType())){
            return false;
        }
        if (targetType != null && !Objects.equals(targetType, getType(target))){
            return false;
        }
        if (selectedType != null && !Objects.equals(selectedType, getType(worldSelection))){
            return false;
        }
        if (hudType != null && !Objects.equals(hudType, getType(hudSelection))){
            return false;
        }
        return true;
    }

    protected void apply(UserInput input, Item target, Item selected)
    {
    }

    private Identifier getType(Item item)
    {
        return item != null ? item.getType() : null;
    }
}