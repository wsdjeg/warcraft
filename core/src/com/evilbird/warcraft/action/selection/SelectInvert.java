/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.selection;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionResult;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.object.AnimatedObject;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.warcraft.common.WarcraftPreferences;
import com.evilbird.warcraft.object.common.capability.SelectableObject;

import javax.inject.Inject;
import java.util.function.Predicate;

import static com.evilbird.engine.common.function.Predicates.either;
import static com.evilbird.warcraft.object.common.query.UnitOperations.isCombatant;
import static com.evilbird.warcraft.object.common.query.UnitOperations.isCorporeal;
import static com.evilbird.warcraft.object.common.query.UnitOperations.isNeutral;
import static com.evilbird.warcraft.object.common.query.UnitPredicates.isAi;
import static com.evilbird.warcraft.object.common.query.UnitPredicates.isNonCombatant;
import static com.evilbird.warcraft.object.common.query.UnitPredicates.isSelected;
import static com.evilbird.warcraft.object.unit.UnitSound.Selected;

/**
 * Instances of this {@link Action} invert the selected status of a given
 * {@link GameObject} in conjunction with deselecting all currently selected items.
 *
 * @author Blair Butterworth
 */
public class SelectInvert extends BasicAction
{
    private transient SelectEvents events;
    private transient WarcraftPreferences preferences;

    @Inject
    public SelectInvert(SelectEvents events, WarcraftPreferences preferences) {
        this.events = events;
        this.preferences = preferences;
    }

    @Override
    public ActionResult act(float delta) {
        SelectableObject item = (SelectableObject) getSubject();
        if (item.getSelected()) {
            deselect(item);
        } else {
            select(item);
        }
        return ActionResult.Complete;
    }

    private void select(SelectableObject entity) {
        updateSelected(entity);
        updateSound(entity);
        setSelected(entity, true);
    }

    private void updateSelected(SelectableObject entity) {
        GameObjectContainer root = entity.getRoot();
        if (isCorporeal(entity) && isCombatant(entity)) {
            setSelected(root, either(isNonCombatant(), isAi()), false);
        } else {
            setSelected(root, isSelected(), false);
        }
    }

    private void updateSound(SelectableObject entity) {
        if (isCorporeal(entity) || isNeutral(entity)) {
            setSelectedSound(entity);
        }
    }

    private void deselect(SelectableObject entity) {
        if (isCombatant(entity)) {
            setSelected(entity, false);
        } else {
            setSelected(entity.getRoot(), isSelected(), false);
        }
    }

    private void setSelectedSound(GameObject entity) {
        if (entity instanceof AnimatedObject && preferences.isAcknowledgementEnabled()) {
            AnimatedObject animatedObject = (AnimatedObject)entity;
            animatedObject.setSound(Selected);
        }
    }

    private void setSelected(GameObjectContainer root, Predicate<GameObject> condition, boolean selected) {
        for (GameObject gameObject : root.findAll(condition)) {
            if (gameObject instanceof SelectableObject) {
                setSelected((SelectableObject) gameObject, selected);
            }
        }
    }

    private void setSelected(SelectableObject selectable, boolean selected) {
        if (selectable.getSelected() != selected) {
            selectable.setSelected(selected);
            events.selectionUpdated(selectable, selected);
        }
    }
}