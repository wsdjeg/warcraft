/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common;

import com.evilbird.engine.action.ActionIdentifier;

/**
 * Defines options of specifying cancel actions varieties.
 *
 * @author Blair Butterworth
 */
public enum CancelActions implements ActionIdentifier
{
    CancelConstruct,
    CancelGather,
    CancelPlaceholder,
    CancelTrain
}