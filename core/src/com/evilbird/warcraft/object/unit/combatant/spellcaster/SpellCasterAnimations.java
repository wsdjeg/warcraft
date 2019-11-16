/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.combatant.spellcaster;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.engine.common.graphics.animation.AnimationCatalog;
import com.evilbird.warcraft.object.unit.combatant.CombatantAssets;

import static com.evilbird.warcraft.object.unit.UnitAnimation.Attack;
import static com.evilbird.warcraft.object.unit.UnitAnimation.CastSpell;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Death;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Idle;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Move;
import static java.util.Objects.requireNonNull;

/**
 * Defines a catalog of spell caster animations as laid out in spell caster
 * texture atlas files.
 *
 * @author Blair Butterworth
 */
public class SpellCasterAnimations extends AnimationCatalog
{
    private static final GridPoint2 SIZE = new GridPoint2(72, 72);

    public SpellCasterAnimations(CombatantAssets assets) {
        this(assets.getBaseTexture());
    }

    public SpellCasterAnimations(Texture general) {
        this(general, SIZE);
    }

    public SpellCasterAnimations(Texture base, GridPoint2 size) {
        super(5);

        requireNonNull(base);
        requireNonNull(size);

        idle(base, size);
        move(base, size);
        death(base, size);
        attack(base, size);
    }

    private void attack(Texture base, GridPoint2 size) {
        alias(CastSpell, Attack);
        animation(Attack)
            .withTexture(base)
            .withSequence(size.y * 5, 5)
            .withSequence(0, 1)
            .withSize(size)
            .withInterval(0.15f)
            .notLooping();
    }

    private void death(Texture base, GridPoint2 size) {
        animation(Death)
            .withTexture(base)
            .withSequence(size.y * 10, (base.getHeight() / size.y) - 10)
            .withSize(size)
            .withInterval(0.15f)
            .notLooping();
    }

    private void idle(Texture base, GridPoint2 size) {
        animation(Idle)
            .withTexture(base)
            .withSequence(0, 1)
            .withSize(size)
            .withInterval(10f)
            .looping();
    }

    private void move(Texture base, GridPoint2 size) {
        animation(Move)
            .withTexture(base)
            .withSequence(0, 5)
            .withSize(size)
            .withInterval(0.10f)
            .looping();
    }
}
