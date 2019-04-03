/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.framework;

import com.evilbird.engine.common.lang.Identifier;

public class FeatureAction extends ParallelAction
{
    private boolean repeats;

    public FeatureAction() {
        super();
    }

    public void repeats() {
        this.repeats = true;
    }

    public void feature(Identifier identifier) {
        setIdentifier(identifier);
    }

    public ScenarioAction scenario(String name) {
        ScenarioAction scenario = new ScenarioAction();
        scenario.setItem(getItem());
        scenario.setTarget(getTarget());
        scenario.setCause(getCause());
        add(scenario);
        return scenario;
    }

    @Override
    public boolean act(float delta) {
        if (actions.isEmpty()){
            features();
        }
        boolean result = super.act(delta);
        if (result && repeats) {
            restart();
            result = false;
        }
        return result;
    }

    protected void features() {
    }
}
