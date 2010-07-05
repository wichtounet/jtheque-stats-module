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
