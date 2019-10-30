/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.display.control.common;

import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.warcraft.item.unit.UnitType;

import java.util.HashMap;
import java.util.Map;

import static com.evilbird.warcraft.item.ui.display.control.common.IconType.Unknown;
import static com.evilbird.warcraft.item.unit.UnitType.AltarOfStorms;
import static com.evilbird.warcraft.item.unit.UnitType.AnduinLothar;
import static com.evilbird.warcraft.item.unit.UnitType.Ballista;
import static com.evilbird.warcraft.item.unit.UnitType.Barracks;
import static com.evilbird.warcraft.item.unit.UnitType.Battleship;
import static com.evilbird.warcraft.item.unit.UnitType.Blacksmith;
import static com.evilbird.warcraft.item.unit.UnitType.BombardTower;
import static com.evilbird.warcraft.item.unit.UnitType.CannonTower;
import static com.evilbird.warcraft.item.unit.UnitType.Castle;
import static com.evilbird.warcraft.item.unit.UnitType.Catapult;
import static com.evilbird.warcraft.item.unit.UnitType.Chogall;
import static com.evilbird.warcraft.item.unit.UnitType.Church;
import static com.evilbird.warcraft.item.unit.UnitType.CircleOfPower;
import static com.evilbird.warcraft.item.unit.UnitType.Daemon;
import static com.evilbird.warcraft.item.unit.UnitType.DarkPortal;
import static com.evilbird.warcraft.item.unit.UnitType.DeathKnight;
import static com.evilbird.warcraft.item.unit.UnitType.Dockyard;
import static com.evilbird.warcraft.item.unit.UnitType.Dragon;
import static com.evilbird.warcraft.item.unit.UnitType.DragonRoost;
import static com.evilbird.warcraft.item.unit.UnitType.DwarvenDemolitionSquad;
import static com.evilbird.warcraft.item.unit.UnitType.ElvenArcher;
import static com.evilbird.warcraft.item.unit.UnitType.ElvenDestroyer;
import static com.evilbird.warcraft.item.unit.UnitType.ElvenRanger;
import static com.evilbird.warcraft.item.unit.UnitType.Encampment;
import static com.evilbird.warcraft.item.unit.UnitType.EyeOfKilrogg;
import static com.evilbird.warcraft.item.unit.UnitType.Farm;
import static com.evilbird.warcraft.item.unit.UnitType.Ferry;
import static com.evilbird.warcraft.item.unit.UnitType.Footman;
import static com.evilbird.warcraft.item.unit.UnitType.Forge;
import static com.evilbird.warcraft.item.unit.UnitType.Fortress;
import static com.evilbird.warcraft.item.unit.UnitType.Foundry;
import static com.evilbird.warcraft.item.unit.UnitType.GiantTurtle;
import static com.evilbird.warcraft.item.unit.UnitType.GnomishFlyingMachine;
import static com.evilbird.warcraft.item.unit.UnitType.GnomishInventor;
import static com.evilbird.warcraft.item.unit.UnitType.GnomishSubmarine;
import static com.evilbird.warcraft.item.unit.UnitType.GoblinAlchemist;
import static com.evilbird.warcraft.item.unit.UnitType.GoblinSappers;
import static com.evilbird.warcraft.item.unit.UnitType.GoblinZeppelin;
import static com.evilbird.warcraft.item.unit.UnitType.GoldMine;
import static com.evilbird.warcraft.item.unit.UnitType.GreatHall;
import static com.evilbird.warcraft.item.unit.UnitType.Grunt;
import static com.evilbird.warcraft.item.unit.UnitType.GryphonAviary;
import static com.evilbird.warcraft.item.unit.UnitType.GryphonRider;
import static com.evilbird.warcraft.item.unit.UnitType.GuardTower;
import static com.evilbird.warcraft.item.unit.UnitType.Guldan;
import static com.evilbird.warcraft.item.unit.UnitType.Keep;
import static com.evilbird.warcraft.item.unit.UnitType.Knight;
import static com.evilbird.warcraft.item.unit.UnitType.LookoutTower;
import static com.evilbird.warcraft.item.unit.UnitType.LumberMill;
import static com.evilbird.warcraft.item.unit.UnitType.Mage;
import static com.evilbird.warcraft.item.unit.UnitType.MageTower;
import static com.evilbird.warcraft.item.unit.UnitType.Metalworks;
import static com.evilbird.warcraft.item.unit.UnitType.Ogre;
import static com.evilbird.warcraft.item.unit.UnitType.OgreJuggernaught;
import static com.evilbird.warcraft.item.unit.UnitType.OgreMage;
import static com.evilbird.warcraft.item.unit.UnitType.OgreMound;
import static com.evilbird.warcraft.item.unit.UnitType.OilPatch;
import static com.evilbird.warcraft.item.unit.UnitType.OilPlatform;
import static com.evilbird.warcraft.item.unit.UnitType.OilRefinery;
import static com.evilbird.warcraft.item.unit.UnitType.OilRig;
import static com.evilbird.warcraft.item.unit.UnitType.OilTanker;
import static com.evilbird.warcraft.item.unit.UnitType.Paladin;
import static com.evilbird.warcraft.item.unit.UnitType.Peasant;
import static com.evilbird.warcraft.item.unit.UnitType.Peon;
import static com.evilbird.warcraft.item.unit.UnitType.PigFarm;
import static com.evilbird.warcraft.item.unit.UnitType.Refinery;
import static com.evilbird.warcraft.item.unit.UnitType.RuneTrap;
import static com.evilbird.warcraft.item.unit.UnitType.Runestone;
import static com.evilbird.warcraft.item.unit.UnitType.ScoutTower;
import static com.evilbird.warcraft.item.unit.UnitType.Seal;
import static com.evilbird.warcraft.item.unit.UnitType.Shipyard;
import static com.evilbird.warcraft.item.unit.UnitType.Skeleton;
import static com.evilbird.warcraft.item.unit.UnitType.Stables;
import static com.evilbird.warcraft.item.unit.UnitType.Stronghold;
import static com.evilbird.warcraft.item.unit.UnitType.TempleOfTheDamned;
import static com.evilbird.warcraft.item.unit.UnitType.TownHall;
import static com.evilbird.warcraft.item.unit.UnitType.Transport;
import static com.evilbird.warcraft.item.unit.UnitType.TrollAxethrower;
import static com.evilbird.warcraft.item.unit.UnitType.TrollBerserker;
import static com.evilbird.warcraft.item.unit.UnitType.TrollDestroyer;
import static com.evilbird.warcraft.item.unit.UnitType.TrollLumberMill;
import static com.evilbird.warcraft.item.unit.UnitType.TrollTanker;
import static com.evilbird.warcraft.item.unit.UnitType.UtherLightbringer;
import static com.evilbird.warcraft.item.unit.UnitType.WatchTower;
import static com.evilbird.warcraft.item.unit.UnitType.Zuljin;

/**
 * Defines the layout of an icon file: a texture containing a set of icons in a
 * predefined order.
 *
 * @author Blair Butterworth
 */
public class UnitIconLayout
{
    private final GridPoint2 size;
    private final Map<Identifier, GridPoint2> locations;

    public UnitIconLayout() {
        size = new GridPoint2(46, 38);
        locations = layout(
            Peasant,                Peon,               Footman,                Grunt,                  ElvenArcher,
            TrollAxethrower,        ElvenRanger,        TrollBerserker,         Knight,                 Ogre,
            Paladin,                OgreMage,           DwarvenDemolitionSquad, GoblinSappers,          Mage,
            DeathKnight,            Ballista,           Catapult,               OilTanker,              TrollTanker,
            Transport,              Ferry,              ElvenDestroyer,         TrollDestroyer,         Battleship,
            OgreJuggernaught,       GnomishSubmarine,   GiantTurtle,            GnomishFlyingMachine,   GoblinZeppelin,
            GryphonRider,           Dragon,             AnduinLothar,           Guldan,               UtherLightbringer,
            Zuljin,                 Chogall,            Daemon,                 Farm,                   PigFarm,
            TownHall,               GreatHall,          Barracks,               Encampment,             LumberMill,
            TrollLumberMill,        Blacksmith,         Forge,                  Shipyard,               Dockyard,
            Refinery,               OilRefinery,        Foundry,                Metalworks,             OilPlatform,
            OilRig,                 Stables,            OgreMound,              GnomishInventor,        GoblinAlchemist,
            ScoutTower,             WatchTower,         Church,                 AltarOfStorms,          MageTower,
            TempleOfTheDamned,      Keep,               Stronghold,             Castle,                 Fortress,
            Castle,                 Fortress,           GryphonAviary,          DragonRoost,            GoldMine,
            GuardTower,             CannonTower,        LookoutTower,           BombardTower,           OilPatch,
            DarkPortal,             CircleOfPower,      Runestone,              Unknown,                Unknown,
            Unknown,                Unknown,            Unknown,                Unknown,                Unknown,
            Unknown,                Unknown,            Unknown,                Unknown,                Unknown,
            Unknown,                Unknown,            RuneTrap,               Unknown,                Unknown,
            Unknown,                Unknown,            Unknown,                Unknown,                Unknown,
            Unknown,                Unknown,            Unknown,                Unknown,                Unknown,
            Unknown,                EyeOfKilrogg,       Unknown,                Unknown,                Skeleton,
            Seal,                   Unknown,            Unknown,                Unknown,                Unknown
        );
    }

    /**
     * Returns the size of the icon with the given {@link UnitType}.
     *
     * @param type  an icon identifier.
     * @return      the size of icon within an icon texture.
     */
    public GridPoint2 getSize(UnitType type) {
        return size;
    }

    /**
     * Returns the icon with the given {@link Identifier}. Icons exist for all
     * units, upgrades, spells and icon types.
     *
     * @param type  an icon identifier.
     * @return      the location of icon within an icon texture.
     */
    public GridPoint2 getLocation(UnitType type) {
        if (type.isCritter()) {
            type = Seal;
        }
        return locations.get(type);
    }

    private Map<Identifier, GridPoint2> layout(Identifier ... types) {
        Map<Identifier, GridPoint2> result = new HashMap<>();
        for (int index = 0; index < types.length; ++index) {
            int column = (index % 5) * size.x;
            int row = (index / 5) * size.y;
            result.put(types[index], new GridPoint2(column, row));
        }
        return result;
    }
}