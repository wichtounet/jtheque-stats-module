package org.jtheque.films.stats.view.impl.panels;

import org.jdesktop.swingx.JXHeader;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.RectangleAnchor;
import org.jtheque.core.managers.Managers;
import org.jtheque.core.managers.beans.IBeansManager;
import org.jtheque.core.managers.language.ILanguageManager;
import org.jtheque.core.managers.language.Internationalizable;
import org.jtheque.core.managers.resource.IResourceManager;
import org.jtheque.core.managers.resource.ImageType;
import org.jtheque.films.stats.StatsModule;
import org.jtheque.films.stats.services.able.IStatsService;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;

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

/**
 * An abstract panel to display stats.
 *
 * @author Baptiste Wicht
 */
public abstract class PanelStats extends JPanel implements Internationalizable {
    private final ILanguageManager resources = Managers.getManager(ILanguageManager.class);

    private final JXHeader header = new JXHeader();

    /**
     * Construct a new PanelStats.
     */
    PanelStats() {
        super();

        Managers.getManager(ILanguageManager.class).addInternationalizable(this);
    }

    /**
     * Return the language manager.
     *
     * @return The language manager.
     */
    final ILanguageManager getResources() {
        return resources;
    }

    /**
     * Set the informations of the header.
     *
     * @param key         The i18n key.
     * @param description The description of the header.
     */
    final void setHeader(String key, String description) {
        header.setTitle(resources.getMessage(key));
        header.setDescription(description);
        header.setIcon(Managers.getManager(IResourceManager.class).getIcon(
                StatsModule.IMAGE_BASE_NAME, "stats-big", ImageType.JPG));
    }

    /**
     * Return the header of the panel.
     *
     * @return The header of the panel.
     */
    public final JXHeader getHeader() {
        return header;
    }

    /**
     * Create a panel containing a pie chart.
     *
     * @param key     The i18 key of the title of the pie chart.
     * @param dataset The dataset used to populate the pie chart.
     * @return The component containing the pie chart.
     */
    final Component createPieChartPanel(String key, PieDataset dataset) {
        JFreeChart pie = ChartFactory.createPieChart3D(resources.getMessage(key), dataset, true, true, true);

        pie.getLegend().setLegendItemGraphicLocation(RectangleAnchor.RIGHT);

        Managers.getManager(ILanguageManager.class).addInternationalizable(new InternationalizableChart(pie, key));

        Component chartPanel = new ChartPanel(pie);

        chartPanel.setBackground(Color.white);

        return chartPanel;
    }

    /**
     * Return the stats service.
     *
     * @return The stats service.
     */
    static IStatsService getStatsService() {
        return Managers.getManager(IBeansManager.class).getBean("statsService");

    }
}