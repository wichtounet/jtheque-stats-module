package org.jtheque.films.stats.view.impl;

/*
 * This file is part of JTheque.
 *
 * JTheque is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 *
 * JTheque is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with JTheque.  If not, see <http://www.gnu.org/licenses/>.
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