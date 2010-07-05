package org.jtheque.films.stats.view.able;

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
