package org.jtheque.films.stats.view.able;

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

import org.jtheque.core.managers.view.able.IWindowView;

import org.jdesktop.swingx.JXHeader;

import javax.swing.JTabbedPane;

/**
 * A Stats view specification.
 *
 * @author Baptiste Wicht
 */
public interface IStatsView extends IWindowView {
    /**
     * Return the tabbed pane used to display the different stats panel.
     *
     * @return The tabbed pane.
     */
    JTabbedPane getTab();

    /**
     * Return the header of the realizer tab.
     *
     * @return The reader of realizers.
     */
    JXHeader getHeaderRealizers();

    /**
     * Return the header of the films tab.
     *
     * @return The reader of films.
     */
    JXHeader getHeaderFilms();

    /**
     * Return the header of the actors tab.
     *
     * @return The reader of actors.
     */
    JXHeader getHeaderActors();
}
