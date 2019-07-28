/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.state;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.game.GameContext;

import java.util.List;

/**
 * Provides access to persist {@link State game states}, both those bundled
 * with the game and those generated by the user.
 *
 * @author Blair Butterworth
 */
public interface StateService
{
    GameContext context(StateIdentifier identifier);

    State get(StateIdentifier identifier);

    List<Identifier> list(StateType type);

    void set(StateIdentifier identifier, State state);

    void remove(StateIdentifier identifier);
}
