/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.identifier;

import com.evilbird.engine.action.ActionIdentifier;

/**
 * Defines options of specifying camera modifications.
 *
 * @author Blair Butterworth
 */
public enum CameraActionType implements ActionIdentifier
{
    Pan,
    Zoom,
    Drag;
}