/*
 * Copyright (c) 2019Placeholder, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * licensePlaceholder, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.placement;

import com.evilbird.engine.item.ItemType;
import com.evilbird.warcraft.item.unit.UnitType;
import org.apache.commons.lang3.Validate;

import static com.evilbird.engine.common.collection.EnumUtils.getName;

/**
 * Defines placeholder varieties.
 *
 * @author Blair Butterworth
 */
public enum PlaceholderType implements ItemType
{
    BarracksPlaceholder,
    BlacksmithPlaceholder,
    CannonTowerPlaceholder,
    CastlePlaceholder,
    ChurchPlaceholder,
    FarmPlaceholder,
    FoundryPlaceholder,
    GnomishInventorPlaceholder,
    GryphonAviaryPlaceholder,
    GuardTowerPlaceholder,
    KeepPlaceholder,
    LumberMillPlaceholder,
    MageTowerPlaceholder,
    OilPlatformPlaceholder,
    RefineryPlaceholder,
    ScoutTowerPlaceholder,
    ShipyardPlaceholder,
    StablesPlaceholder,
    TownHallPlaceholder,

    AltarOfStormsPlaceholder,
    ForgePlaceholder,
    EncampmentPlaceholder,
    BombardTowerPlaceholder,
    DockyardPlaceholder,
    DragonRoostPlaceholder,
    FortressPlaceholder,
    MetalworksPlaceholder,
    GoblinAlchemistPlaceholder,
    GreatHallPlaceholder,
    LookoutTowerPlaceholder,
    OgreMoundPlaceholder,
    OilRefineryPlaceholder,
    OilRigPlaceholder,
    PigFarmPlaceholder,
    StrongholdPlaceholder,
    TempleOfTheDamnedPlaceholder,
    TrollLumberMillPlaceholder,
    WatchTowerPlaceholder;

    public UnitType getBuilding() {
        return UnitType.valueOf(getName(this, "", "Placeholder"));
    }

    public static PlaceholderType forBuilding(UnitType unitType) {
        Validate.isTrue(unitType.isBuilding());
        return PlaceholderType.valueOf(unitType.name() + "Placeholder");
    }
}
