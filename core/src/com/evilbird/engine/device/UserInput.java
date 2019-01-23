/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.device;

import com.badlogic.gdx.math.Vector2;

public class UserInput
{
    private UserInputType type;
    private Vector2 position;
    private Vector2 delta;
    private int count;

    // TODO: Remove count parameter
    public UserInput(UserInputType type, Vector2 position, int count) {
        this(type, position, Vector2.Zero, 1);
    }

    public UserInput(UserInputType type, Vector2 position, Vector2 delta, int count) {
        this.type = type;
        this.position = position;
        this.delta = delta;
        this.count = count;
    }

    public UserInputType getType() {
        return type;
    }

    public Vector2 getPosition() {
        return this.position;
    }

    public Vector2 getDelta() {
        return this.delta;
    }

    public int getCount() {
        return this.count;
    }
}
