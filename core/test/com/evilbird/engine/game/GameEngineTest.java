/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.game;

import com.evilbird.engine.game.error.ErrorScreen;
import com.evilbird.engine.game.loader.LoaderScreen;
import com.evilbird.engine.menu.Menu;
import com.evilbird.engine.menu.MenuFactory;
import com.evilbird.engine.menu.MenuOverlay;
import com.evilbird.engine.menu.MenuScreen;
import com.evilbird.engine.state.StateScreen;
import com.evilbird.engine.state.StateService;
import com.evilbird.test.testcase.GameTestCase;
import org.junit.Before;
import org.junit.Test;

import static com.evilbird.warcraft.menu.main.MainMenuType.Campaign;
import static com.evilbird.warcraft.menu.main.MainMenuType.Home;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Instances of this unit test validate the {@link GameEngine} class.
 *
 * @author Blair Butterworth
 */
public class GameEngineTest extends GameTestCase
{
    private GameEngine engine;
    private MenuScreen menuScreen;
    private MenuFactory menuFactory;

    @Before
    public void setup() {
        ErrorScreen errorScreen = mock(ErrorScreen.class);
        LoaderScreen loaderScreen = mock(LoaderScreen.class);
        menuScreen = mock(MenuScreen.class);
        MenuOverlay menuOverlay = mock(MenuOverlay.class);
        StateScreen stateScreen = mock(StateScreen.class);
        menuFactory = mock(MenuFactory.class);
        StateService stateService = mock(StateService.class);
        GameAssets gameAssets = mock(GameAssets.class);
        engine = new GameEngine(
            errorScreen, loaderScreen, menuScreen, menuOverlay,
            stateScreen, menuFactory, stateService, gameAssets);
    }

    @Test
    public void showRootMenuTest() {
        engine.showMenu();
        verify(menuScreen).show();
    }

    @Test
    public void showMenuTest() {
        Menu menu = mock(Menu.class);
        when(menuFactory.get(Campaign)).thenReturn(menu);

        engine.showMenu(Campaign);
        verify(menuScreen).setMenu(menu, Campaign);
        verify(menuScreen).show();
    }

    @Test
    public void showMenuAfterMenuTest() {
        Menu homeMenu = mock(Menu.class);
        Menu campaignMenu = mock(Menu.class);

        when(menuFactory.get(Home)).thenReturn(homeMenu);
        when(menuFactory.get(Campaign)).thenReturn(campaignMenu);

        engine.showMenu(Campaign);
        engine.showMenu(Home);

        verify(menuScreen, times(1)).setMenu(campaignMenu, Campaign);
        verify(menuScreen, times(1)).setMenu(homeMenu, Home);
        verify(menuScreen, times(2)).dispose();
        verify(menuScreen, times(2)).show();
    }
}