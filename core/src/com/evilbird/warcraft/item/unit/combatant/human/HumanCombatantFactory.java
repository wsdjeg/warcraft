/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.combatant.human;

import com.evilbird.engine.common.inject.IdentifiedAssetProviderSet;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.Unit;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

import javax.inject.Inject;

/**
 * Instances of this factory create Human {@link Combatant Combatants}, a
 * {@link Unit} specialization that can move and attack other Units.
 *
 * @author Blair Butterworth
 */
public class HumanCombatantFactory extends IdentifiedAssetProviderSet<Item>
{
    @Inject
    public HumanCombatantFactory(
        BallistaFactory ballistaFactory,
        BattleshipFactory battleshipFactory,
        DwarvenDemolitionSquadFactory dwarvenDemolitionSquadFactory,
        ElvenArcherFactory elvenArcherFactory,
        ElvenArcherCaptiveFactory elvenArcherCaptiveFactory,
        ElvenDestroyerFactory elvenDestroyerFactory,
        FootmanFactory footmanFactory,
        GnomishFlyingMachineFactory gnomishFlyingMachineFactory,
        GnomishSubmarineFactory gnomishSubmarineFactory,
        GryphonRiderFactory gryphonRiderFactory,
        KnightFactory knightFactory,
        MageFactory mageFactory,
        TransportFactory transportFactory)
    {
        addProvider(UnitType.Ballista, ballistaFactory);
        addProvider(UnitType.Battleship, battleshipFactory);
        addProvider(UnitType.DwarvenDemolitionSquad, dwarvenDemolitionSquadFactory);
        addProvider(UnitType.ElvenArcher, elvenArcherFactory);
        addProvider(UnitType.ElvenArcherCaptive, elvenArcherCaptiveFactory);
        addProvider(UnitType.ElvenDestroyer,  elvenDestroyerFactory);
        addProvider(UnitType.Footman, footmanFactory);
        addProvider(UnitType.GnomishFlyingMachine, gnomishFlyingMachineFactory);
        addProvider(UnitType.GnomishSubmarine, gnomishSubmarineFactory);
        addProvider(UnitType.GryphonRider, gryphonRiderFactory);
        addProvider(UnitType.Knight, knightFactory);
        addProvider(UnitType.Mage, mageFactory);
        addProvider(UnitType.Transport, transportFactory);
    }
}
