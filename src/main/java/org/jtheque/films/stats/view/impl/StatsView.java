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

import org.jtheque.core.managers.Managers;
import org.jtheque.core.managers.error.JThequeError;
import org.jtheque.core.managers.language.ILanguageManager;
import org.jtheque.core.managers.language.TabTitleUpdater;
import org.jtheque.core.managers.view.able.IViewManager;
import org.jtheque.core.managers.view.impl.frame.abstraction.SwingDialogView;
import org.jtheque.films.stats.view.able.IStatsView;
import org.jtheque.films.stats.view.impl.panels.PanelStats;

import org.jdesktop.swingx.JXHeader;
import org.springframework.stereotype.Component;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import java.awt.Container;
import java.awt.Frame;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * A view to display the stats.
 *
 * @author Baptiste Wicht
 */
public final class StatsView extends SwingDialogView implements IStatsView {
    private static final long serialVersionUID = 1L;

    /**
     * The tabbed pane of the view.
     */
    private JTabbedPane tab;

    private final PanelStats panelFilms;
    private final PanelStats panelActors;
    private final PanelStats panelRealizers;

    private static final int DEFAULT_WIDTH = 650;
    private static final int DEFAULT_HEIGHT = 600;

    /**
     * Construct a new StatsView modal to the parent view.
     *
     * @param parent         The parent frame.
     * @param panelFilms     The panel films.
     * @param panelActors    The panel actors.
     * @param panelRealizers The panel realizers.
     */
    public StatsView(Frame parent, PanelStats panelFilms, PanelStats panelActors, PanelStats panelRealizers) {
        super(parent);

        setJMenuBar(new JMenuBarStats());

        this.panelFilms = panelFilms;
        this.panelActors = panelActors;
        this.panelRealizers = panelRealizers;

        build();
    }

    /**
     * Build the view.
     */
    private void build() {
        setContentPane(buildContentPane());
        setTitleKey("stats.view.title");

        Managers.getManager(IViewManager.class).configureView(this, "stats", DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    /**
     * Build the content pane.
     *
     * @return The content pane.
     */
    private Container buildContentPane() {
        tab = new JTabbedPane();
        tab.setTabPlacement(JTabbedPane.TOP);

        Map<JComponent, String> components = new HashMap<JComponent, String>(3);

        addTab(components, panelFilms, "stats.view.tab.films");
        addTab(components, panelActors, "stats.view.tab.actors");
        addTab(components, panelRealizers, "stats.view.tab.realizers");

        Managers.getManager(ILanguageManager.class).addInternationalizable(new TabTitleUpdater(tab, components));

        return tab;
    }

    private void addTab(Map<JComponent, String> components, PanelStats panel, String key) {
        JComponent component = new JScrollPane(panel);

        components.put(component, key);
        tab.addTab(Managers.getManager(ILanguageManager.class).getMessage(key), component);
    }

    @Override
    public void closeDown() {
        Managers.getManager(IViewManager.class).saveState(this, "main");

        super.closeDown();
    }

    @Override
    public JTabbedPane getTab() {
        return tab;
    }

    @Override
    public JXHeader getHeaderRealizers() {
        return panelRealizers.getHeader();
    }

    @Override
    public JXHeader getHeaderFilms() {
        return panelFilms.getHeader();
    }

    @Override
    public JXHeader getHeaderActors() {
        return panelActors.getHeader();
    }

    @Override
    protected void validate(Collection<JThequeError> errors) {
    }
}