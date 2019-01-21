/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.hud.control.actions;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.Navigable;
import com.evilbird.engine.common.lang.Objects;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemOperations;
import com.evilbird.engine.item.control.GridPane;
import com.evilbird.warcraft.item.common.capability.ResourceContainer;
import com.evilbird.warcraft.item.common.capability.ResourceIdentifier;
import com.evilbird.warcraft.item.data.DataType;
import com.evilbird.warcraft.item.hud.HudControls;
import org.apache.commons.lang3.Validate;

import java.util.*;

import javax.inject.Inject;

/**
 * Instances of this class show a grid of buttons representing the actions that
 * the selected items can perform.
 *
 * @author Blair Butterworth
 */
//TODO: Disable buttons if insufficient resources
//TODO: Disable buttons if constructing
public class ActionPane extends GridPane implements Navigable
{
    private Collection<Item> selection;
    private Map<ResourceIdentifier, Float> resources;
    private ActionButtonProvider buttonProvider;
    private ActionPaneLayout layout;
    private boolean cancelShown;

    @Inject
    public ActionPane(ActionButtonProvider buttonProvider) {
        super(3, 3);

        this.buttonProvider = buttonProvider;
        this.selection = Collections.emptyList();
        this.resources = Collections.emptyMap();
        this.layout = ActionPaneLayout.Actions;
        this.cancelShown = false;

        setSize(176, 176);
        setCellPadding(3);
        setCellWidthMinimum(54);
        setCellHeightMinimum(46);
        setId(HudControls.ActionPane);
        setType(HudControls.ActionPane);
        setTouchable(Touchable.childrenOnly);
    }

    public void setSelection(Collection<Item> newSelection) {
        if (viewInvalidated(newSelection)) {
            resetLayout();
            updateModel(newSelection);
            updateView(newSelection);
        }
    }

    @Override
    public void navigate(Identifier location) {
        Validate.isInstanceOf(ActionPaneLayout.class, location);
        layout = (ActionPaneLayout)location;
        updateModel(selection);
        updateView(selection);
    }

    private boolean viewInvalidated(Collection<Item> newSelection) {
        return selectionUpdated(newSelection) || actionsUpdated(newSelection); //|| resourcesUpdated(newSelection);
    }

    private boolean selectionUpdated(Collection<Item> newSelection) {
        return ! Objects.equals(selection, newSelection);
    }

    private boolean actionsUpdated(Collection<Item> newSelection) {
        boolean showCancel = hasAction(newSelection);
        return cancelShown != showCancel;
    }

    private boolean resourcesUpdated(Collection<Item> newSelection) {
        Map<ResourceIdentifier, Float> newResources = getResources(newSelection);
        return ! Objects.equals(resources, newResources);
    }

    private boolean hasAction(Collection<Item> selection) {
        if (selection.size() == 1) {
            Array<Action> actions = selection.iterator().next().getActions();
            return actions.size > 0;
        }
        return false;
    }

    private Map<ResourceIdentifier, Float> getResources(Collection<Item> selection) {
        if (! selection.isEmpty()) {
            Item item = selection.iterator().next();
            Item player = ItemOperations.findAncestorByType(item, DataType.Player);
            return new HashMap<>(((ResourceContainer)player).getResources());
        }
        return Collections.emptyMap();
    }

    private void resetLayout() {
        layout = ActionPaneLayout.Actions;
    }

    private void updateModel(Collection<Item> newSelection) {
        selection = newSelection;
        resources = getResources(newSelection);
    }

    private void updateView(Collection<Item> selection) {
        clearCells();

        if (layout == ActionPaneLayout.Actions && hasAction(selection)){
            showCancelAction();
        }
        else {
            showActions(selection);
        }
    }

    private void showCancelAction() {
        ActionButton cancelButton = getButton(ActionButtonType.CancelButton);
        setCell(cancelButton, 2, 2);
        cancelShown = true;
    }

    private void showActions(Collection<Item> selection) {
        List<ActionButtonType> actions = getActionButtons(selection);
        setCells(getTiles(actions));
        cancelShown = false;
    }

    private List<ActionButtonType> getActionButtons(Collection<Item> selection) {
        switch (layout) {
            case Actions: return getItemActionButtons(selection);
            case SimpleBuildings: return ActionButtonAssociations.getSimpleBuildingButtons();
            case AdvancedBuildings: return ActionButtonAssociations.getAdvancedBuildingButtons();
            default: throw new UnsupportedOperationException();
        }
    }

    private List<ActionButtonType> getItemActionButtons(Collection<Item> selection) {
        List<ActionButtonType> result = new ArrayList<>();
        Iterator<Item> selectionIterator = selection.iterator();

        if (selectionIterator.hasNext()) {
            Item item = selectionIterator.next();
            result.addAll(ActionButtonAssociations.getActionButtons(item));

            while (selectionIterator.hasNext()) {
                item = selectionIterator.next();
                result.retainAll(ActionButtonAssociations.getActionButtons(item));
            }
        }
        return result;
    }

//    private Collection<ActionIdentifier> getAvailableActions(Item item) {
//        if (item instanceof Unit){
//            Unit unit = (Unit)item;
//            return unit.getAvailableActions();
//        }
//        return Collections.emptyList();
//    }

    private Collection<Item> getTiles(Collection<ActionButtonType> actions) {
        Collection<Item> result = new ArrayList<>(actions.size());
        for (ActionButtonType action: actions){
            result.add(getButton(action));
        }
        return result;
    }

    private ActionButton getButton(ActionButtonType action) {
        ActionButton result = buttonProvider.get(action);
        //result.setTouchable(meetsResourceRequirements(action) ? Touchable.enabled : Touchable.disabled);
        return result;
    }

//    private boolean meetsResourceRequirements(ActionIdentifier action) {
//        if (action instanceof ResourceRequirement) {
//            ResourceRequirement requirementAction = (ResourceRequirement)action;
//            Map<ResourceIdentifier, Float> requirements = requirementAction.getResourceRequirements();
//
//            for (Map.Entry<ResourceIdentifier, Float> requirement: requirements.entrySet()) {
//                Float playerResource = requireNonNull(resources.get(requirement.getKey()), 0f);
//                Float requiredResource = requirement.getValue();
//
//                if (playerResource < requiredResource) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
}
