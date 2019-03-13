/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.state;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.Persisted;

import java.io.IOException;
import java.util.List;

public interface StateService extends Persisted
{
    List<Identifier> list(StateType type) throws IOException;

    State get(StateIdentifier identifier) throws IOException;

    void set(StateIdentifier identifier, State state) throws IOException;

    void remove(StateIdentifier identifier) throws IOException;
}
