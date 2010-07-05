package org.jtheque.films.stats.view.impl;

/*
 * Copyright JTheque (Baptiste Wicht)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.jtheque.core.managers.view.impl.components.menu.JThequeMenu;
import org.jtheque.core.managers.view.impl.components.menu.JThequeMenuItem;
import org.jtheque.films.stats.view.impl.actions.AcCloseStatsView;
import org.jtheque.films.stats.view.impl.actions.AcRefreshStats;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import java.awt.event.KeyEvent;

/**
 * MenuBar for the stats view.
 *
 * @author Baptiste Wicht
 */
public final class JMenuBarStats extends JMenuBar {
    private static final long serialVersionUID = 3470421907899720330L;

    /**
     * Construct a new JMenuBarStats for the specified actions.
     */
    public JMenuBarStats() {
        super();

        JMenu menu = new JThequeMenu("menu.stats");

        JMenuItem menuItem = new JThequeMenuItem(new AcRefreshStats());
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
        menu.add(menuItem);

        menu.addSeparator();

        menu.add(new JThequeMenuItem(new AcCloseStatsView()));

        add(menu);
    }
}
