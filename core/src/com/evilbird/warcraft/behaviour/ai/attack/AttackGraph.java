/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.ai.attack;

import com.evilbird.engine.common.collection.Maps;
import com.evilbird.engine.events.Events;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.object.spatial.GameObjectGraph;
import com.evilbird.engine.object.spatial.GameObjectNode;
import com.evilbird.warcraft.action.common.create.CreateEvent;
import com.evilbird.warcraft.action.common.remove.RemoveEvent;
import com.evilbird.warcraft.action.move.MoveEvent;
import com.evilbird.warcraft.object.common.capability.OffensiveObject;
import com.evilbird.warcraft.object.common.capability.PerishableObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Represents a mapping between spatial locations and attackers sight-lines,
 * allowing easy retrieval of those potential attackers within range of a given
 * target.
 *
 * @author Blair Butterworth
 */
public class AttackGraph
{
    private Events events;
    private GameObjectGraph graph;
    private Map<GameObjectNode, List<OffensiveObject>> attackers;

    public AttackGraph(GameObjectGraph graph, Events events) {
        this.events = events;
        this.graph = graph;
        this.attackers = new HashMap<>();
    }

    public List<OffensiveObject> getAttackers(PerishableObject target) {
        List<OffensiveObject> result = new ArrayList<>();
        for (GameObjectNode node: graph.getNodes(target)) {
            List<OffensiveObject> attackersWithinRange = attackers.get(node);
            if (attackersWithinRange != null) {
                result.addAll(attackersWithinRange);
            }
        }
        return result;
    }

    public List<PerishableObject> getTargets(OffensiveObject attacker) {
        List<PerishableObject> result = new ArrayList<>();
        for (GameObjectNode node: graph.getNodes(attacker.getPosition(), attacker.getSize(), attacker.getSight())) {
            for (GameObject occupant: node.getOccupants()) {
                if (occupant instanceof PerishableObject) {
                    result.add((PerishableObject)occupant);
                }
            }
        }
        return result;
    }

    public void initialize(GameObjectContainer state) {
        for (GameObject gameObject : state.findAll(OffensiveObject.class::isInstance)) {
            addAttacker((OffensiveObject) gameObject);
        }
    }

    public void update() {
        evaluateCreateEvents();
        evaluateRemoveEvents();
        evaluateMoveEvents();
    }

    private void evaluateCreateEvents() {
        for (CreateEvent event: events.getEvents(CreateEvent.class)) {
            GameObject subject = event.getSubject();
            if (subject instanceof OffensiveObject) {
                addAttacker((OffensiveObject)subject);
            }
        }
    }

    private void evaluateRemoveEvents() {
        for (RemoveEvent event: events.getEvents(RemoveEvent.class)) {
            GameObject subject = event.getSubject();
            if (subject instanceof OffensiveObject) {
                removeAttacker((OffensiveObject)subject);
            }
        }
    }

    private void evaluateMoveEvents() {
        for (MoveEvent event: events.getEvents(MoveEvent.class)) {
            GameObject subject = event.getSubject();
            if (subject instanceof OffensiveObject){
                updateAttacker((OffensiveObject)subject);
            }
        }
    }

    private void addAttacker(OffensiveObject attacker) {
        for (GameObjectNode node: graph.getNodes(attacker.getPosition(), attacker.getSize(), attacker.getSight())) {
            List<OffensiveObject> occupants = Maps.getOrDefault(attackers, node, ArrayList::new);
            occupants.add(attacker);
            attackers.put(node, occupants);
        }
    }

    private void removeAttacker(OffensiveObject combatant) {
        for (Entry<GameObjectNode, List<OffensiveObject>> entry: attackers.entrySet()) {
            entry.getValue().remove(combatant);
        }
    }

    private void updateAttacker(OffensiveObject combatant) {
        removeAttacker(combatant);
        addAttacker(combatant);
    }
}
