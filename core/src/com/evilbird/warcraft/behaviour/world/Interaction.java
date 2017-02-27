package com.evilbird.warcraft.behaviour.world;

import com.evilbird.engine.device.UserInputType;
import com.evilbird.warcraft.action.Actions;

//TODO: Replace with more generalized behaviour, introgating all game state;
public class Interaction
{
    private UserInputType inputType;
    private String targetType;
    private String selectedType;
    private Actions commandType;

    public Interaction(Interaction action)
    {
        this.inputType = action.inputType;
        this.targetType = action.targetType;
        this.selectedType = action.selectedType;
        this.commandType = action.commandType;
    }

    public Interaction(UserInputType inputType, String targetType, String selectedType, Actions commandType)
    {
        this.inputType = inputType;
        this.targetType = targetType;
        this.selectedType = selectedType;
        this.commandType = commandType;
    }

    public UserInputType getInputType()
    {
        return inputType;
    }

    public String getTargetType()
    {
        return targetType;
    }

    public String getSelectedType()
    {
        return selectedType;
    }

    public Actions getCommandType()
    {
        return commandType;
    }
}
